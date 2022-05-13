package com.proxibid.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proxibid.entity.BidderHistoryItem;
import org.springframework.stereotype.Service;
import com.proxibid.entity.CartItem;
import com.proxibid.repository.BidderHistoryItemRepository;
import com.proxibid.util.PaymentStatus;

@Service
public class BidderHistoryItemService {

	@Autowired
	private BidderHistoryItemRepository historyItemRepository;

	public BidderHistoryItem save(BidderHistoryItem bidderHistoryItem) {
		return historyItemRepository.save(bidderHistoryItem);

	}

	public List<BidderHistoryItem> findAllByUsername(String sellerId) {
		return historyItemRepository.findAllBySellerId(sellerId);
	}

	// prepare cart Item
	public BidderHistoryItem prepareHistoryItem(CartItem cartItem) {

		BidderHistoryItem historyCartItem = new BidderHistoryItem();

		// create new cart item
		historyCartItem.setBidderId(cartItem.getBidderId());
		historyCartItem.setSellerId(cartItem.getSellerId());
		historyCartItem.setName(cartItem.getName());
		historyCartItem.setDescription(cartItem.getDescription());
		historyCartItem.setImage(cartItem.getImage());
		historyCartItem.setPrice(cartItem.getPrice());
		historyCartItem.setAuctionTitle(cartItem.getAuctionTitle());
		historyCartItem.setCategory(cartItem.getCategory());
		historyCartItem.setPaymentStatus(PaymentStatus.PAID.toString());
		historyCartItem.setEventDatetime(cartItem.getEventDatetime());
		historyCartItem.setTimestamp(LocalDateTime.now());
		return historyCartItem;

	}
}
