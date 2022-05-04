package com.proxibid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxibid.entity.Auctioneer;

public interface AuctioneerRepository extends JpaRepository<Auctioneer, String> {

	Auctioneer save(Auctioneer auctioneer);

	Optional<Auctioneer> findByEmail(String email);

	boolean existsByEmail(String email);
}
