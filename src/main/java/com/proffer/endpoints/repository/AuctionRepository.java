package com.proffer.endpoints.repository;

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

	@Query(value = "SELECT * FROM auction ORDER BY start_date limit 3", nativeQuery = true)
	List<Auction> findFirstThree();

	List<Auction> findAllByCategoryLike(String category);

	List<Auction> findAllByCategoryContaining(String category);
}
