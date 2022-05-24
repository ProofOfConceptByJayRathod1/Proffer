package com.proxibid.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Auction;
import com.proxibid.repository.AuctionRepository;

@Service
public class AuctionService {

	@Autowired
	private AuctionRepository auctionRepository;

	public Auction findAuctionCategoryTitleAndSellerIdById(Long id) {
		return auctionRepository.findAuctionCategoryTitleAndSellerIdById(id).orElse(null);
	}

	public List<Auction> findAllByCategoryContaining(String category) {
		return auctionRepository.findAllByCategoryContaining(category);
	}

	public List<Auction> findAllUpcomingByCategoryContaining(String category) {
		return auctionRepository.findUpcomingAuctionsByCategory(LocalDateTime.now(), category);
	}

	public List<Auction> getTodaysEvents() {
		return auctionRepository.findTodaysEvents();
	}

	public List<Auction> getAll() {
		return auctionRepository.findAll();
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

	public List<Auction> findUpcomingEvents() {
		return auctionRepository.findUpcomingEvents();
	}

	public List<Auction> findPastAuctionByAuctioneer(String username) {
		return auctionRepository.findPastAuctionsByUsername(username,LocalDateTime.now());
	}

}
