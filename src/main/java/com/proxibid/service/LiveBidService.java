package com.proxibid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.proxibid.entity.LiveBid;
import com.proxibid.repository.LiveBidRepository;

@Service
//@CacheConfig(cacheNames = "LiveBid")
public class LiveBidService {

	@Autowired
	private LiveBidRepository liveBidRepository;

	// @CachePut(key = "#liveBid.id", condition = "#liveBid.id!=null")
	public LiveBid save(LiveBid liveBid) {
		return liveBidRepository.save(liveBid);
	}

	// @CacheEvict(key = "#id")
	public void removeById(Long id) {
		liveBidRepository.deleteById(id);
	}

	// @Cacheable(key = "#auctiontId", condition = "#auctiontId!=null")
	public List<LiveBid> findAllByAuctionId(long auctionId) {
		return liveBidRepository.findAllByAuctionId(auctionId);
	}

	// @CachePut(key = "#id")
	public LiveBid findById(Long id) {
		return liveBidRepository.findById(id).get();
	}

	// @CachePut(key = "#itemId")
	public LiveBid findByItemId(Long itemId) {
		return liveBidRepository.findByItemId(itemId).get();
	}
}
