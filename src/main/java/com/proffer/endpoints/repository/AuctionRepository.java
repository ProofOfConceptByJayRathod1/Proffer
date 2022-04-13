package com.proffer.endpoints.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proffer.endpoints.entity.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

	Object findAllByCategoryIn(ArrayList<String> selectedCategory);

	ArrayList<Auction> findAllByCategory(String selectedCategory);

	Auction findByeventNo(long eventNo);

	List<Auction> findAll();

	@Query(value = "SELECT * FROM auction limit 3", nativeQuery = true)
	List<Auction> findFirstThree();

	List<Auction> findAllByCategoryLike(String category);

	List<Auction> findAllByCategoryContaining(String category);

	@Query(value = "SELECT * FROM auction a WHERE a.start_date = CURRENT_DATE", nativeQuery = true)
	List<Auction> findTodaysEvents();

	@Query(value = "SELECT * FROM auction a WHERE a.start_date > CURRENT_DATE", nativeQuery = true)
	List<Auction> findUpcomingEvents();

	@Query(value = "SELECT * FROM auction a WHERE a.start_date < CURRENT_DATE", nativeQuery = true)
	List<Auction> findPastEvents();

	@Query(value = "SELECT * FROM auction a WHERE a.date < ?1 AND a.end_date_time > ?1 AND a.seller_id=?2", nativeQuery = true)
	List<Auction> findLiveAuctions(LocalDateTime dateTime, String username);

	@Query(value = "SELECT * FROM auction a WHERE a.end_date_time < ?1", nativeQuery = true)
	List<Auction> findPastAuctions(LocalDateTime dateTime);

	@Query(value = "SELECT * FROM auction a WHERE a.start_date > CURRENT_DATE AND a.date > ?1 AND a.seller_id=?2", nativeQuery = true)
	List<Auction> findTodaysUpcomingEvents(LocalDateTime dateTime, String username);

	@Query(value = "SELECT * FROM auction a WHERE a.date < ?1 AND a.end_date_time > ?1", nativeQuery = true)
	List<Auction> findLiveAuctions(LocalDateTime now);

	@Query(value = "SELECT * FROM auction a WHERE a.start_date = CURRENT_DATE AND a.start_time > ?1", nativeQuery = true)
	List<Auction> findTodaysUpcomingEvents(LocalTime time);

	@Query(value = "SELECT * FROM auction a WHERE a.date < ?1 AND a.end_date_time > ?1 AND a.category LIKE %?2%", nativeQuery = true)
	List<Auction> findLiveAuctionsByCategory(LocalDateTime now, String category);

	@Query(value = "SELECT * FROM auction a WHERE a.date >= ?1 AND a.category LIKE %?2%", nativeQuery = true)
	List<Auction> findUpcomingAuctionsByCategory(LocalDateTime now, String category);
}
