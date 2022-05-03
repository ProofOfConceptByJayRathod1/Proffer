package com.proxibid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxibid.entity.BidWinner;

@Repository
public interface BidWinnerRepository extends JpaRepository<BidWinner, Long> {

}
