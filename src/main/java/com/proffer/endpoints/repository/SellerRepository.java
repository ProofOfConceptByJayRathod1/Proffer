package com.proffer.endpoints.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String> {

	Seller save(Seller seller);

	Optional<Seller> findByEmail(String email);

	boolean existsByEmail(String email);
}
