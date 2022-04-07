package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.bid;


public interface BidRepository extends JpaRepository<bid, Long> {

}
