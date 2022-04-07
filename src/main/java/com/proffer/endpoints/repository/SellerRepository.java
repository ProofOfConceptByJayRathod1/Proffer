package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

	Seller save(Seller seller);
	
	Seller findByEmail(String sellerid);

}
