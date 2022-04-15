package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proffer.endpoints.entity.BidderCartItem;

@Repository
public interface BidderCartItemRepository extends JpaRepository<BidderCartItem, Long> {

}
