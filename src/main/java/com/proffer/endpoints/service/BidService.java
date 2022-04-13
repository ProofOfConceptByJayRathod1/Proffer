package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.repository.BidRepository;

@Service
public class BidService {

	@Autowired
	private BidRepository bidRepository;

	public Bid saveBid(Bid message) {
		return bidRepository.save(message);

	}

	public Bid getCurrentBid(Long itemId) {
		return bidRepository.findByBidTime(itemId);
	}

}
