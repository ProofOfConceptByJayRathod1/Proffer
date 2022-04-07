package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
	
		Catalog save(Catalog catalog);	
}
