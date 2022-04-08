package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Bidder;
import com.proffer.endpoints.repository.BidderRepository;

@Service
public class BidderDestailsService implements UserDetailsService {

	@Autowired
	private BidderRepository bidderRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Bidder bidder = bidderRepository.findByBidderEmail(username).orElseThrow(() -> {
			throw new UsernameNotFoundException("Bidder with " + username + " does not exists!");
		});
		return bidder;
	}

}
