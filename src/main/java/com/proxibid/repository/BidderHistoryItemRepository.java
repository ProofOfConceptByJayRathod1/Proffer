package com.proxibid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxibid.entity.BidderHistoryItem;

public interface BidderHistoryItemRepository extends JpaRepository<BidderHistoryItem, Long> {

	List<BidderHistoryItem> findAllBySellerId(String sellerId);

}
