package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.BidderCart;
import com.proffer.endpoints.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public BidderCart findByBidderId(String bidderId) {
		return cartRepository.findByBidderId(bidderId).orElse(null);
	}

	public BidderCart save(BidderCart cart) {
		return cartRepository.save(cart);
	}

}
