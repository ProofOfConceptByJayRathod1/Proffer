package com.proxibid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Catalog;
import com.proxibid.repository.CatalogRepository;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	public List<Catalog> getAll() {

		return catalogRepository.findAll();
	}

	public List<Catalog> getFirstEight() {
		return catalogRepository.findFirstEight();
	}

	public List<Catalog> getRandomFive() {
		return catalogRepository.findRandomEight();
	}

	public List<Catalog> findByKeyword(String keyword) {
		return catalogRepository.search(keyword);
	}

	public void save(Catalog c) {
		catalogRepository.save(c);
	}
}
