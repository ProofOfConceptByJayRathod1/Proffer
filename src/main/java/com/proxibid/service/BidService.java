package com.proxibid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Bid;
import com.proxibid.repository.BidRepository;

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
