package com.proxibid.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.BidderCart;
import com.proxibid.entity.BidderHistoryItem;
import com.proxibid.entity.HistoryCart;
import com.proxibid.entity.LiveBid;
import com.proxibid.repository.HistoryCartRepository;

@Service
public class HistoryCartService {

	@Autowired
	private HistoryCartRepository cartRepository;

	@Autowired
	private BidderHistoryItemService historyItemService;

	public HistoryCart findByBidderId(String bidderId) {
		return cartRepository.findByBidderId(bidderId).orElse(null);
	}

	public HistoryCart save(HistoryCart cart) {
		return cartRepository.save(cart);
	}

	public HistoryCart prepareCart(BidderCart cart) {
		HistoryCart historyCart = new HistoryCart();

		historyCart.setBidderId(cart.getBidderId());

		List<BidderHistoryItem> items = new ArrayList<>();

		cart.getCartItems().forEach(i -> {
			items.add(historyItemService.prepareHistoryItem(i));
		});

		historyCart.setHistoryItems(items);
		return historyCart;
	}

}
