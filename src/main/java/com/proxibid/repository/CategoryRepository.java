package com.proxibid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxibid.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
