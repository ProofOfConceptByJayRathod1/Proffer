package com.proxibid.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Auction;
import com.proxibid.entity.CartItem;
import com.proxibid.entity.LiveBid;
import com.proxibid.repository.CartItemRepository;
import com.proxibid.util.PaymentStatus;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);

	}

	public List<CartItem> findAllByUsername(String sellerId) {
		return cartItemRepository.findAllBySellerId(sellerId);
	}

	// prepare cart Item
	public CartItem prepareCartItem(LiveBid bid, Auction auction) {

		CartItem cartItem = new CartItem();

		// create new cart item
		cartItem.setBidderId(bid.getBidderId());
		cartItem.setSellerId(auction.getSellerId());
		cartItem.setName(bid.getCatalog().getItemName());
		cartItem.setDescription(bid.getCatalog().getItemDesc());
		cartItem.setImage(bid.getCatalog().getItemImage());
		cartItem.setPrice(bid.getCurrentBidValue());
		cartItem.setAuctionTitle(auction.getEventTitle());
		cartItem.setCategory(auction.getCategory());
		cartItem.setPaymentStatus(PaymentStatus.PENDING.toString());
		cartItem.setEventDatetime(LocalDateTime.now());
		return cartItem;

	}

}
