package com.proffer.endpoints.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.BidderCartItem;
import com.proffer.endpoints.repository.BidderCartItemRepository;

@Service
public class BidderCartItemService {

	@Autowired
	private BidderCartItemRepository cartItemRepository;

	public BidderCartItem save(BidderCartItem cartItem) {
		return cartItemRepository.save(cartItem);

	}
}
