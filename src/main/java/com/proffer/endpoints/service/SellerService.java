package com.proffer.endpoints.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Seller;
import com.proffer.endpoints.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	public void saveSeller(Seller seller) {
		sellerRepository.save(seller);

	}

	public boolean checkIfSellerEmailIdAlreadyExistInTheDatabase(Seller seller) {
		if (sellerRepository.findByEmail(seller.getEmail()) != null)
			return true;
		return false;
	}

	public boolean existsByEmail(String email) {
		return sellerRepository.existsByEmail(email);
	}

	public Seller findByEmail(String sellerId) {
		return sellerRepository.findByEmail(sellerId).get();

	}
}
