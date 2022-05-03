package com.proxibid.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxibid.entity.LiveBid;

@Repository
public interface LiveBidRepository extends JpaRepository<LiveBid, Long> {

	List<LiveBid> findAllByAuctionId(long auctionId);

	Optional<LiveBid> findById(Long id);

	@Query(value = "SELECT * FROM live_bid b WHERE b.item_id = ?1", nativeQuery = true)
	Optional<LiveBid> findByItemId(Long itemId);

}
