package com.proxibid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proxibid.entity.Auctioneer;
import com.proxibid.entity.Bidder;
import com.proxibid.repository.BidderRepository;
import com.proxibid.repository.AuctioneerRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AuctioneerRepository repository;

	@Autowired
	private BidderRepository bidderRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Auctioneer auctioneer = repository.findByEmail(email).orElse(null);

		if (auctioneer == null) {
			Bidder bidder = bidderRepository.findByBidderEmail(email).orElse(null);
			if (bidder == null) {
				throw new UsernameNotFoundException("User not found!");
			}
			return bidder;
		} else {
			return auctioneer;
		}

	}
}
