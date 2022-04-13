package com.proffer.endpoints.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.repository.AuctionRepository;

@Service
public class AuctionService {

	@Autowired
	private AuctionRepository auctionRepository;

	public List<Auction> getTodaysEvents() {
		return auctionRepository.findTodaysEvents();
	}

	public List<Auction> getByCategory(String category) {
		return auctionRepository.findAllByCategory(category);
	}

	public Auction findByeventNo(long eventNo) {
		return auctionRepository.findByeventNo(eventNo);
	}

	public void save(Auction auction) {
		auctionRepository.save(auction);
	}

	public List<Auction> findTodaysUpcomingEventsByUsername(String username) {
		return auctionRepository.findTodaysUpcomingEvents(LocalDateTime.now(), username);
	}

	public List<Auction> findLiveByUsername(String username) {
		return auctionRepository.findLiveAuctions(LocalDateTime.now(), username);
	}

	public List<Auction> findAllLiveEvents() {
		return auctionRepository.findLiveAuctions(LocalDateTime.now());
	}

	public List<Auction> findTodaysUpcomingEvents() {
		return auctionRepository.findTodaysUpcomingEvents(LocalTime.now());
	}

	public List<Auction> getAllLiveEventsByCategory(String category) {
		return auctionRepository.findLiveAuctionsByCategory(LocalDateTime.now(), category);
	}

}
