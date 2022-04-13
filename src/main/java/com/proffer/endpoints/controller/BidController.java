package com.proffer.endpoints.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.repository.AuctionRepository;
import com.proffer.endpoints.repository.BidRepository;
import com.proffer.endpoints.repository.BidWinnerRepository;
import com.proffer.endpoints.service.BidService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.util.JwtUtil;
import com.proffer.endpoints.util.LiveBidStatus;

@Controller
public class BidController {

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	AuctionRepository auctionRepository;
	@Autowired
	private BidRepository bidRepository;
	@Autowired
	private BidService bidService;
	@Autowired
	private BidWinnerRepository bidWinnerRepository;

	@Autowired
	private BidWinnerService bidWinnerService;

	@RequestMapping("/public/PlaceBid")
	@ResponseBody
	public LiveBid placeBid(@RequestParam Long id, @RequestParam String bidderId, @RequestParam int bidValue)
			throws Exception {
		LiveBid bid = liveBidService.findById(id);
		bid.setBidderId(bidderId);
		bid.setBidStatus(LiveBidStatus.LIVE.toString());
		bid.setBidTime(LocalTime.now());
		bid.setCurrentBidValue(bidValue);
		return liveBidService.save(bid);
	}

	@RequestMapping("/public/CloseBid")
	@ResponseBody
	public LiveBid closeBid(@RequestParam Long id, @RequestParam String bidderId, @RequestParam int bidValue)
			throws Exception {
		LiveBid bid = liveBidService.findById(id);
		bid.setBidderId(bidderId);
		bid.setBidStatus(LiveBidStatus.SOLD.toString());
		bid.setBidTime(LocalTime.now());
		bid.setCurrentBidValue(bidValue);

		BidWinner bidWinner = new BidWinner();
		bidWinner.setBidderId(bidderId);
		bidWinner.setAmount(bidValue);
		bidWinner.setEventNo(bid.getAuctionId());
		bidWinner.setTimestamp(LocalDateTime.now());

		bidWinnerService.save(bidWinner);
		return liveBidService.save(bid);
	}

	@MessageMapping("/UpdateLiveBid")
	@SendTo("/bid/RefreshFeed")
	public String updateLiveBid() throws Exception {
		return "Success!";
	}

	@MessageMapping("/hello1")
	@SendTo("/bid/placebid")
	public Bid set(Bid message) throws Exception {
		message.setBidTime(LocalTime.now());
		bidRepository.save(message);
		return new Bid(message.getBidValue(), message.getItemId(), message.getBidderEmail());
	}

	@MessageMapping("/hello2")
	@SendTo("/bid/win")
	public BidWinner win(BidWinner bidWinner) throws Exception {
		bidWinnerRepository.save(bidWinner);
		return new BidWinner(bidWinner.getBidderId(), bidWinner.getEventNo(), bidWinner.getItemId(),
				bidWinner.getAmount());
	}

	@RequestMapping(value = "/auctionhouse/bid", method = RequestMethod.GET)
	public String getbid() {
		return "Auctioneer_bid";
	}

	@RequestMapping(value = "/auctionhouse/bidtest", method = RequestMethod.GET)
	public String bidtest() {
		return "bidding-test";
	}

}
