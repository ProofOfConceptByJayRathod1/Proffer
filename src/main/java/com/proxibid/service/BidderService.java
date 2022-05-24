package com.proxibid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Bidder;
import com.proxibid.repository.BidderRepository;

@Service
public class BidderService {
	@Autowired
	private BidderRepository bidderRepository;

	public void bidderSignUp(Bidder bidder) {
		bidderRepository.save(bidder);
	}

	public boolean bidderExistsByEmail(String email) {
		return bidderRepository.existsByBidderEmail(email);
	}

	public Bidder findByEmail(String bidderEmail) {
		return bidderRepository.findByBidderEmail(bidderEmail).orElse(null);
	}
}
