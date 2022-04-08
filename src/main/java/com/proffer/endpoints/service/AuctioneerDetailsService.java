package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proffer.endpoints.entity.Seller;
import com.proffer.endpoints.repository.SellerRepository;

@Service
public class AuctioneerDetailsService implements UserDetailsService {

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Seller seller = sellerRepository.findByEmail(username).orElseThrow(() -> {
			throw new UsernameNotFoundException("Bidder with " + username + " does not exists!");
		});
		return seller;
	}

}
