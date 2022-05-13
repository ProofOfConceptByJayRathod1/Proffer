package com.proxibid.schedulers;

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

import com.proxibid.entity.Auction;
import com.proxibid.entity.BidWinner;
import com.proxibid.entity.BidderCart;
import com.proxibid.entity.CartItem;
import com.proxibid.entity.Catalog;
import com.proxibid.entity.LiveBid;
import com.proxibid.service.AuctionService;
import com.proxibid.service.BidWinnerService;
import com.proxibid.service.CartItemService;
import com.proxibid.service.CartService;
import com.proxibid.service.CatalogService;
import com.proxibid.service.LiveBidService;
import com.proxibid.util.AuctionStatus;
import com.proxibid.util.LiveBidStatus;
import com.proxibid.util.PaymentStatus;

@Component
public class AuctionScheduler {

	@Autowired
	private Scheduler scheduler;

	private static final Logger log = LoggerFactory.getLogger(AuctionScheduler.class);

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private BidWinnerService bidWinnerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CatalogService catalogService;

	@Scheduled(cron = "0 59 10 ? * *")
	public void scheduleLiveAuction() {

		List<Auction> todaysAuction = auctionService.getTodaysEvents();
		// set initial live bid at start of the day
		// and save in database
		todaysAuction.forEach(a -> {

			a.getItems().forEach(item -> {
				LiveBid liveBid = new LiveBid();

				liveBid.setAuctionId(a.getEventNo());
				liveBid.setBidderId(LiveBidStatus.NONE.toString());
				liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
				liveBid.setBidTime(LocalTime.now());
				liveBid.setBidDate(LocalDate.now());
				liveBid.setCurrentBidValue(item.getItemStartBid());
				liveBid.setCatalog(item);
				liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());

				liveBidService.save(liveBid);
				log.info("Item id : " + item.getItemId() + " added for live bid.");
			});

		});

		scheduleAuctionEndings();

	}

	// ends auction automatically and declares bid winner
	public void scheduleAuctionEndings() {

		List<Auction> todaysAuction = auctionService.getTodaysEvents();

		todaysAuction.forEach((auction) -> {

			// schedule event for all auction to end
			scheduler.scheduleTodaysAuctionEnding(() -> {

				// set auction status to OVER and save
				auction.setStatus(AuctionStatus.OVER.toString());
				auctionService.save(auction);

				List<Catalog> auCatalogs = auction.getItems();
				auCatalogs.forEach(item -> {

					LiveBid liveBid = liveBidService.findByItemId(item.getItemId());

					BidWinner bidWinner = null;
					if (liveBid.getBidStatus().equals(LiveBidStatus.LIVE.toString())) {

						bidWinner = bidWinnerService.prepareBidWinner(liveBid);
						bidWinnerService.save(bidWinner);

						// create and save cart
						cartService.prepareAndSaveCart(liveBid);

						// remove live bid after winner is declared
						liveBidService.removeById(liveBid.getId());

					} else if (liveBid.getBidStatus().equals(LiveBidStatus.INITIAL.toString())) {

						bidWinner = bidWinnerService.prepareBidWinner(liveBid);
						bidWinnerService.save(bidWinner);

						// remove from live bid even if item did not get any bid
						liveBidService.removeById(liveBid.getId());
					}

					// update catalog status
					Catalog catalog = liveBid.getCatalog();
					catalog.setWinner(bidWinner);
					catalog.setBidStatus(liveBid.getBidStatus());
					catalogService.save(catalog);
				});
			}, auction.getEndDateTime(), auction.getEventTitle());

		});

	}

}
