package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.BidWinner;

public interface BidWinnerRepository extends JpaRepository<BidWinner, Long>{

}
