package com.proxibid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Auctioneer;
import com.proxibid.repository.AuctioneerRepository;

@Service
public class AuctioneerService {

	@Autowired
	private AuctioneerRepository auctioneerRepository;

	public void saveSeller(Auctioneer auctioneer) {
		auctioneerRepository.save(auctioneer);

	}

	public boolean checkIfSellerEmailIdAlreadyExistInTheDatabase(Auctioneer auctioneer) {
		if (auctioneerRepository.findByEmail(auctioneer.getEmail()) != null)
			return true;
		return false;
	}

	public boolean existsByEmail(String email) {
		return auctioneerRepository.existsByEmail(email);
	}

	public Auctioneer findByEmail(String sellerId) {
		return auctioneerRepository.findByEmail(sellerId).get();

	}
}
