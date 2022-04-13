package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proffer.endpoints.entity.BidWinner;

@Repository
public interface BidWinnerRepository extends JpaRepository<BidWinner, Long> {

}
