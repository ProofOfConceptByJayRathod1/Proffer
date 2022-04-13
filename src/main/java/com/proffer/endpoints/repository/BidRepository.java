package com.proffer.endpoints.repository;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proffer.endpoints.entity.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

	@Query(value = "SELECT * FROM bid WHERE item_id = ?1 ORDER BY bid_time desc limit 1", nativeQuery = true)
	Bid findByBidTime(long itemId);

}
