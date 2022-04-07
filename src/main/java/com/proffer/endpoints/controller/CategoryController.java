package com.proffer.endpoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proffer.endpoints.entity.Category;
import com.proffer.endpoints.service.CategoryService;

@RestController
@RequestMapping("category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryservice;

	@GetMapping("/getAll")
	public List<Category> getAllCategories() {
		return categoryservice.getAllCategories();
	}
}