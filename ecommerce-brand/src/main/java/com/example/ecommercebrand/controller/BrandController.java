package com.example.ecommercebrand.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommercebrand.entity.Brand;
import com.example.ecommercebrand.service.BrandService;

@RestController
@RequestMapping(value = "api/brand")
public class BrandController {

	Logger logger = LoggerFactory.getLogger(BrandController.class);

	@Autowired
	private BrandService brandService;

	
	/**
	 * Retrieves a {@link brand} object by id
	 * @param brandId
	 * @return
	 */
	@GetMapping(value = "/brandId/{brandId}")
	public ResponseEntity<Brand> getBrandById(@PathVariable("brandId") Long brandId) {
		Brand brand = brandService.getBrandById(brandId);
		if (brand != null) {
			return ResponseEntity.ok(brand);
		}
		return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * Create a new brand
	 * @param brand
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Brand> addBrand(@RequestBody @Valid Brand brand) {
		Brand savedBrand = brandService.createBrand(brand);
		if (brand != null) {
			return ResponseEntity.ok(savedBrand);
		}
		return ResponseEntity.noContent().build();
	}	

}
