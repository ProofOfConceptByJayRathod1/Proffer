package com.proffer.endpoints.schedulers;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.util.AuctionStatus;
import com.proffer.endpoints.util.LiveBidStatus;

@Component
public class JobScheduler {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private BidWinnerService bidWinnerService;

	@Scheduled(fixedDelay = 1000 * 60)
	public void runEveryMinute() {
		System.out.println("I will execute every minute");
	}

	@Scheduled(cron = "0 35 16 ? * *")
	public void runNow() {

		auctionService.getTodaysEvents().forEach((a) -> {

			// schedule event for all auction to end
			scheduler.scheduleToday(() -> {

				// set auction status to OVER and save
				a.setStatus(AuctionStatus.OVER.toString());
				auctionService.save(a); // schedule to end auction

				// check for bid winner and save in BidWinner
				a.getItems().forEach(item -> {
					LiveBid liveBid = liveBidService.findByItemId(item.getItemId());

					if (liveBid.getBidStatus().equals(LiveBidStatus.LIVE.toString())) {
						BidWinner bidWinner = new BidWinner();
						bidWinner.setAmount(liveBid.getCurrentBidValue());
						bidWinner.setBidderId(liveBid.getBidderId());
						bidWinner.setEventNo(liveBid.getAuctionId());
						bidWinner.setItemId(liveBid.getCatalog().getItemId());
						bidWinner.setTimestamp(LocalDateTime.now());

						bidWinnerService.save(bidWinner);

						liveBid.setBidStatus(LiveBidStatus.SOLD.toString());
						liveBidService.save(liveBid);
					} else if (liveBid.getBidStatus().equals(LiveBidStatus.INITIAL.toString())) {

						BidWinner bidWinner = new BidWinner();
						bidWinner.setAmount(liveBid.getCurrentBidValue());
						bidWinner.setBidderId(liveBid.getBidderId());
						bidWinner.setEventNo(liveBid.getAuctionId());
						bidWinner.setItemId(liveBid.getCatalog().getItemId());
						bidWinner.setTimestamp(LocalDateTime.now());

						bidWinnerService.save(bidWinner);
						liveBid.setBidStatus(LiveBidStatus.PASS.toString());
						liveBidService.save(liveBid);
					}

				});
			}, a.getEndDateTime());

		});

	}

	@Scheduled(cron = "0 16 10 ? * *")
	public void runEveryMidnight() {
		// System.out.println("I will intrupt every minute");
		auctionService.getTodaysEvents().forEach(a -> {
			System.out.println(a.getEventNo());
			a.getItems().forEach(item -> {
				LiveBid liveBid = new LiveBid();

				liveBid.setAuctionId(a.getEventNo());
				liveBid.setBidderId("None");
				liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
				liveBid.setBidTime(LocalTime.now());
				liveBid.setCurrentBidValue(item.getItemStartBid());
				liveBid.setCatalog(item);
				liveBidService.save(liveBid);
			});

		});
	}
}
