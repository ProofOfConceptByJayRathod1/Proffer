package com.proffer.endpoints.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.BidderCart;
import com.proffer.endpoints.entity.CartItem;
import com.proffer.endpoints.entity.Catalog;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.CartItemService;
import com.proffer.endpoints.service.CartService;
import com.proffer.endpoints.service.CatalogService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.util.LiveBidStatus;
import com.proffer.endpoints.util.PaymentStatus;

@Controller
public class BidController {

	@Autowired
	private LiveBidService liveBidService;

	@Autowired
	private BidWinnerService bidWinnerService;

	@Autowired
	private BidService bidService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CatalogService catalogService;

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
		bid.setEventNo(liveBid.getAuctionId());

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
		// update live bid
		liveBidService.save(bid);

		BidWinner bidWinner = bidWinnerService.prepareBidWinner(bid);
		// save bid winner
		bidWinnerService.save(bidWinner);

		BidderCart cart = cartService.prepareCart(bid);
		cartService.save(cart);

		// update catalog status
		Catalog catalog = bid.getCatalog();
		catalog.setWinner(bidWinner);
		catalog.setBidStatus(bid.getBidStatus());
		catalogService.save(catalog);
		return bid;
	}

	@PostMapping("/public/setSecodaryStatus")
	@ResponseBody
	public String setSecondaryStatus(@RequestParam Long id, @RequestParam String bidderId, @RequestParam int bidValue,
			@RequestParam String status) {

		LiveBid bid = liveBidService.findById(id);

		switch (status) {
		case "NONE":
			bid.setSecondaryStatus(LiveBidStatus.ONCE.toString());
			break;
		case "ONCE":
			bid.setSecondaryStatus(LiveBidStatus.TWICE.toString());
			break;
		case "TWICE":
			bid.setSecondaryStatus(LiveBidStatus.SOLD.toString());
			bid.setBidStatus(LiveBidStatus.SOLD.toString());

			// save bid winner
			BidWinner bidWinner = bidWinnerService.prepareBidWinner(bid);
			bidWinnerService.save(bidWinner);

			// save or update cart
			BidderCart cart = cartService.prepareCart(bid);
			cartService.save(cart);
			break;
		}

		// update live bid
		liveBidService.save(bid);

		return "Updated successfully!";
	}

	@MessageMapping("/UpdateLiveBid")
	@SendTo("/bid/RefreshFeed")
	public String updateLiveBid() throws Exception {
		return "Feed  refreshed successfully!";
	}

}
