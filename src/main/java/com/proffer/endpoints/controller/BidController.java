package com.proffer.endpoints.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.BidderCart;
import com.proffer.endpoints.entity.BidderCartItem;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidService;
import com.proffer.endpoints.service.BidWinnerService;
import com.proffer.endpoints.service.BidderCartItemService;
import com.proffer.endpoints.service.CartService;
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
	private AuctionService auctionService;

	@Autowired
	private BidderCartItemService cartItemService;

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

		BidWinner bidWinner = new BidWinner();
		bidWinner.setBidderId(bidderId);
		bidWinner.setAmount(bidValue);
		bidWinner.setEventNo(bid.getAuctionId());
		bidWinner.setTimestamp(now);
		bidWinner.setItemId(bid.getCatalog().getItemId());
		// save bid winner
		bidWinnerService.save(bidWinner);

		BidderCart cart = cartService.findByBidderId(bidderId);
		if (cart == null) {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			BidderCartItem cartItem = new BidderCartItem();

			// create new cart item
			cartItem.setBidderId(bidderId);
			cartItem.setSellerId(auction.getSellerId());
			cartItem.setName(bid.getCatalog().getItemName());
			cartItem.setDescription(bid.getCatalog().getItemDesc());
			cartItem.setImage(bid.getCatalog().getItemImage());
			cartItem.setPrice(bidValue);
			cartItem.setAuctionTitle(auction.getEventTitle());
			cartItem.setCategory(auction.getCategory());
			cartItem.setPaymentStatus(PaymentStatus.PENDING.toString());
			cartItem.setEventDatetime(now);
			cartItemService.save(cartItem);

			// create new cart
			cart = new BidderCart();
			cart.setBidderId(bidderId);
			cart.setCartItems(Arrays.asList(cartItem));
			cart.setTotalAmount(bidValue);
			cartService.save(cart);

		} else {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			BidderCartItem cartItem = new BidderCartItem();

			// create new cart item
			cartItem.setBidderId(bidderId);
			cartItem.setSellerId(auction.getSellerId());
			cartItem.setName(bid.getCatalog().getItemName());
			cartItem.setDescription(bid.getCatalog().getItemDesc());
			cartItem.setImage(bid.getCatalog().getItemImage());
			cartItem.setPrice(bidValue);
			cartItem.setAuctionTitle(auction.getEventTitle());
			cartItem.setCategory(auction.getCategory());
			cartItem.setPaymentStatus(PaymentStatus.PENDING.toString());
			cartItem.setEventDatetime(now);

			// update cart
			double totalPrice = cart.getCartItems().stream().map(x -> x.getPrice()).reduce(0, (i1, i2) -> i1 + i2);

			cart.setTotalAmount(totalPrice);
			List<BidderCartItem> cartItems = cart.getCartItems();
			cartItems.add(cartItem);
			cart.setCartItems(cartItems);
			cartService.save(cart);
		}
		return bid;
	}

	@MessageMapping("/UpdateLiveBid")
	@SendTo("/bid/RefreshFeed")
	public String updateLiveBid() throws Exception {
		return "Feed  refreshed successfully!";
	}

}
