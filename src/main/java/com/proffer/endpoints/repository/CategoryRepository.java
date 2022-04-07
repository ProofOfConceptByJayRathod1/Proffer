package com.proffer.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proffer.endpoints.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
