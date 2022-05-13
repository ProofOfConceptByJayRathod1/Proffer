package com.proxibid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxibid.entity.HistoryCart;

@Repository
public interface HistoryCartRepository extends JpaRepository<HistoryCart, Long> {

	Optional<HistoryCart> findByBidderId(String bidderId);

}
