package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Bidder;
import com.proffer.endpoints.repository.BidderRepository;

@Service
public class BidderService {
	@Autowired
	private BidderRepository bidderRepository;
	
	public void bidderSignUp(Bidder bidder)
	{
		bidderRepository.save(bidder);
	}
	
	
	
}
