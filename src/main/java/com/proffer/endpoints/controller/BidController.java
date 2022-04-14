package com.proffer.endpoints.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.BidService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.util.LiveBidStatus;

@Controller
public class BidController {

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private BidWinnerService bidWinnerService;

	@Autowired
	private BidService bidService;

	@RequestMapping("/public/PlaceBid")
	@ResponseBody
	public LiveBid placeBid(@RequestParam Long id, @RequestParam String bidderId, @RequestParam int bidValue)
			throws Exception {

		LocalDateTime now = LocalDateTime.now();

		LiveBid liveBid = liveBidService.findById(id);
		liveBid.setBidderId(bidderId);
		liveBid.setBidStatus(LiveBidStatus.LIVE.toString());
		liveBid.setBidTime(now.toLocalTime());
		liveBid.setBidDate(now.toLocalDate());
		liveBid.setCurrentBidValue(bidValue);

		Bid bid = new Bid();
		bid.setBidderEmail(bidderId);
		bid.setBidTime(now.toLocalTime());
		bid.setBidDate(now.toLocalDate());
		bid.setBidValue(bidValue);
		bid.setItemId(liveBid.getCatalog().getItemId());
		bid.setBidStatus(liveBid.getBidStatus());

		// save bid for log
		bidService.saveBid(bid);
		// update live bid
		return liveBidService.save(liveBid);
	}

	@RequestMapping("/public/CloseBid")
	@ResponseBody
	public LiveBid closeBid(@RequestParam Long id, @RequestParam String bidderId, @RequestParam int bidValue)
			throws Exception {

		LocalDateTime now = LocalDateTime.now();

		LiveBid bid = liveBidService.findById(id);
		bid.setBidderId(bidderId);
		bid.setBidStatus(LiveBidStatus.SOLD.toString());
		bid.setBidDate(now.toLocalDate());
		bid.setBidTime(now.toLocalTime());
		bid.setCurrentBidValue(bidValue);

		BidWinner bidWinner = new BidWinner();
		bidWinner.setBidderId(bidderId);
		bidWinner.setAmount(bidValue);
		bidWinner.setEventNo(bid.getAuctionId());
		bidWinner.setTimestamp(now);

		// save bid winner
		bidWinnerService.save(bidWinner);
		// update live bid
		return liveBidService.save(bid);
	}

	@MessageMapping("/UpdateLiveBid")
	@SendTo("/bid/RefreshFeed")
	public String updateLiveBid() throws Exception {
		return "Feed  refreshed successfully!";
	}

}
