package com.proffer.endpoints.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Bid;
import com.proffer.endpoints.entity.LiveBid;
import com.proffer.endpoints.repository.LiveBidRepository;

@Service
public class LiveBidService {
	@Autowired
	private LiveBidRepository liveBidRepository;

	public LiveBid save(LiveBid liveBid) {
		return liveBidRepository.save(liveBid);
	}

	public List<LiveBid> findAllByAuctionId(long auctionId) {
		return liveBidRepository.findAllByAuctionId(auctionId);
	}

	public LiveBid findById(Long id) {
		return liveBidRepository.findById(id).get();
	}

	public LiveBid findByItemId(Long itemId) {
		return liveBidRepository.findByItemId(itemId).get();
	}
}
