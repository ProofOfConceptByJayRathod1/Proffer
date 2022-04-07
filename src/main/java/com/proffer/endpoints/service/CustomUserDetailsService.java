package com.proffer.endpoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proffer.endpoints.entity.Bidder;
import com.proffer.endpoints.entity.Seller;
import com.proffer.endpoints.repository.BidderRepository;
import com.proffer.endpoints.repository.SellerRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SellerRepository repository;
    
    @Autowired
    private BidderRepository bidderRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
        Seller seller = repository.findByEmail(email);
    
        if(seller == null)
        {
        	Bidder bidder=bidderRepository.findByBidderEmail(email);
        	
        
        	 return new org.springframework.security.core.userdetails.User(bidder.getBidderEmail(), bidder.getBidderPassword(), new ArrayList<>());
        }
        else
        {
        	 return new org.springframework.security.core.userdetails.User(seller.getEmail(), seller.getPassword(), new ArrayList<>());
        }
       
    }
}
