package com.proffer.endpoints.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proffer.endpoints.entity.BidderCart;

@Repository
public interface CartRepository extends JpaRepository<BidderCart, Long> {

	Optional<BidderCart> findByBidderId(String bidderId);

}
