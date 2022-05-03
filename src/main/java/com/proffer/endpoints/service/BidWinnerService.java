package com.proffer.endpoints.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.repository.BidWinnerRepository;

@Service
public class BidWinnerService {

	@Autowired
	private BidWinnerRepository winnerRepository;

	public BidWinner save(BidWinner bidWinner) {
		return winnerRepository.save(bidWinner);
	}

	public BidWinner findById(Long id) {
		return winnerRepository.findById(id).get();
	}

	public List<BidWinner> findAll() {
		return winnerRepository.findAll();
	}

	public BidWinner prepareBidWinner(LiveBid bid) {

		BidWinner bidWinner = new BidWinner();
		bidWinner.setBidderId(bid.getBidderId());
		bidWinner.setAmount(bid.getCurrentBidValue());
		bidWinner.setEventNo(bid.getAuctionId());
		bidWinner.setTimestamp(LocalDateTime.now());
		bidWinner.setItemId(bid.getCatalog().getItemId());
		return bidWinner;
	}
}
