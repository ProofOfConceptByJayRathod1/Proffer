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
import com.proffer.endpoints.entity.CartItem;
import com.proffer.endpoints.entity.Catalog;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.CartItemService;
import com.proffer.endpoints.service.CartService;
import com.proffer.endpoints.service.CatalogService;
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
	private CartItemService cartItemService;

	@Autowired
	private CatalogService catalogService;

	// change scheduler's time before running app
	@Scheduled(cron = "0 59 10 ? * *")
	public void runEveryMidnight() {

		List<Auction> todaysAuction = auctionService.getTodaysEvents();
		// set initial live bid at start of the day
		// and save in database
		todaysAuction.forEach(a -> {

			a.getItems().forEach(item -> {
				LiveBid liveBid = new LiveBid();

				liveBid.setAuctionId(a.getEventNo());
				liveBid.setBidderId("None");
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

		// ends auction automatically and declares bid winner
		runNow();

	}

	// ends auction automatically and declares bid winner
	public void runNow() {

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
