package com.proffer.endpoints.schedulers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.BidderCart;
import com.proffer.endpoints.entity.BidderCartItem;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.BidderCartItemService;
import com.proffer.endpoints.service.CartService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.util.AuctionStatus;
import com.proffer.endpoints.util.LiveBidStatus;
import com.proffer.endpoints.util.PaymentStatus;

@Component
public class JobScheduler {

	@Autowired
	private Scheduler scheduler;

	private static final Logger log = LoggerFactory.getLogger(JobScheduler.class);

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private BidWinnerService bidWinnerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private BidderCartItemService cartItemService;

	// change scheduler's time before running app
	@Scheduled(cron = "0 12 11 ? * *")
	public void runEveryMidnight() {

		// set initial live bid at start of the day
		// and save in database
		auctionService.getTodaysEvents().forEach(a -> {

			a.getItems().forEach(item -> {
				LiveBid liveBid = new LiveBid();

				liveBid.setAuctionId(a.getEventNo());
				liveBid.setBidderId("None");
				liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
				liveBid.setBidTime(LocalTime.now());
				liveBid.setBidDate(LocalDate.now());
				liveBid.setCurrentBidValue(item.getItemStartBid());
				liveBid.setCatalog(item);
				liveBidService.save(liveBid);
				log.info("Item id : " + item.getItemId() + " added for live bid.");
			});

		});

		// ends auction automatically and declares bid winner
		runNow();

	}

	// ends auction automatically and declares bid winner
	public void runNow() {

		auctionService.getTodaysEvents().forEach((a) -> {

			// schedule event for all auction to end
			scheduler.scheduleTodaysAuctionEnding(() -> {

				// set auction status to OVER and save
				a.setStatus(AuctionStatus.OVER.toString());
				auctionService.save(a); // schedule to end auction

				// check for bid winner and save in BidWinner
				a.getItems().forEach(item -> {
					LiveBid liveBid = liveBidService.findByItemId(item.getItemId());
					LocalDateTime now = LocalDateTime.now();

					if (liveBid.getBidStatus().equals(LiveBidStatus.LIVE.toString())) {
						BidWinner bidWinner = new BidWinner();
						bidWinner.setAmount(liveBid.getCurrentBidValue());
						bidWinner.setBidderId(liveBid.getBidderId());
						bidWinner.setEventNo(liveBid.getAuctionId());
						bidWinner.setItemId(liveBid.getCatalog().getItemId());
						bidWinner.setTimestamp(now);

						bidWinnerService.save(bidWinner);

						BidderCart cart = cartService.findByBidderId(liveBid.getBidderId());
						if (cart == null) {

							BidderCartItem cartItem = new BidderCartItem();

							// create new cart item
							cartItem.setBidderId(liveBid.getBidderId());
							cartItem.setName(liveBid.getCatalog().getItemName());
							cartItem.setDescription(liveBid.getCatalog().getItemDesc());
							cartItem.setImage(liveBid.getCatalog().getItemImage());
							cartItem.setPrice(liveBid.getCurrentBidValue());
							cartItem.setAuctionTitle(a.getEventTitle());
							cartItem.setCategory(a.getCategory());
							cartItem.setPaymentStatus(PaymentStatus.PENDING.toString());
							cartItem.setEventDatetime(now);
							cartItem.setSellerId(a.getSellerId());
							cartItemService.save(cartItem);

							// create new cart
							cart = new BidderCart();
							cart.setBidderId(liveBid.getBidderId());
							cart.setCartItems(Arrays.asList(cartItem));
							cart.setTotalAmount(liveBid.getCurrentBidValue());
							cartService.save(cart);

						} else {

							BidderCartItem cartItem = new BidderCartItem();

							// create new cart item
							cartItem.setBidderId(liveBid.getBidderId());
							cartItem.setName(liveBid.getCatalog().getItemName());
							cartItem.setDescription(liveBid.getCatalog().getItemDesc());
							cartItem.setImage(liveBid.getCatalog().getItemImage());
							cartItem.setPrice(liveBid.getCurrentBidValue());
							cartItem.setAuctionTitle(a.getEventTitle());
							cartItem.setCategory(a.getCategory());
							cartItem.setPaymentStatus(PaymentStatus.PENDING.toString());
							cartItem.setEventDatetime(now);

							// update cart
							double totalPrice = cart.getCartItems().stream().map(x -> x.getPrice()).reduce(0,
									(i1, i2) -> i1 + i2);
							cart.setTotalAmount(totalPrice);
							List<BidderCartItem> cartItems = cart.getCartItems();
							cartItems.add(cartItem);
							cart.setCartItems(cartItems);
							cartService.save(cart);
						}

						// remove live bid after winner is declared
						liveBidService.removeById(liveBid.getId());

					} else if (liveBid.getBidStatus().equals(LiveBidStatus.INITIAL.toString())) {

						BidWinner bidWinner = new BidWinner();
						bidWinner.setAmount(liveBid.getCurrentBidValue());
						bidWinner.setBidderId(liveBid.getBidderId());
						bidWinner.setEventNo(liveBid.getAuctionId());
						bidWinner.setItemId(liveBid.getCatalog().getItemId());
						bidWinner.setTimestamp(LocalDateTime.now());

						bidWinnerService.save(bidWinner);

						// remove from live bid even if item did not get any bid
						liveBidService.removeById(liveBid.getId());
					}

				});
			}, a.getEndDateTime(), a.getEventTitle());

		});

	}

}
