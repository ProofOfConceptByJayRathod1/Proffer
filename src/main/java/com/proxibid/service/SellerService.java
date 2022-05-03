package com.proxibid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Auctioneer;
import com.proxibid.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	public void saveSeller(Auctioneer auctioneer) {
		sellerRepository.save(auctioneer);

	}

	public boolean checkIfSellerEmailIdAlreadyExistInTheDatabase(Auctioneer auctioneer) {
		if (sellerRepository.findByEmail(auctioneer.getEmail()) != null)
			return true;
		return false;
	}

	public boolean existsByEmail(String email) {
		return sellerRepository.existsByEmail(email);
	}

	public Auctioneer findByEmail(String sellerId) {
		return sellerRepository.findByEmail(sellerId).get();

	}
}
