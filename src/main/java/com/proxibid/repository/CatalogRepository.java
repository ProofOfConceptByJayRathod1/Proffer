package com.proxibid.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proxibid.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

	Catalog save(Catalog catalog);

	@Query(value = "SELECT * from catalog limit 8", nativeQuery = true)
	List<Catalog> findFirstEight();

	@Query(value = "SELECT * from catalog order by item_name limit 5", nativeQuery = true)
	List<Catalog> findRandomEight();

	@Query(value = "SELECT * FROM catalog c WHERE c.item_desc LIKE %?1%"
			+ " OR c.item_name LIKE %?1%", nativeQuery = true)
	List<Catalog> search(String keyword);
}
