package com.example.ecommercebrand.service;

import java.util.List;

import com.example.ecommercebrand.entity.Brand;

public interface BrandService {

	/**
	 * Create a brand {@link Brand} 
	 * @param brand
	 * @return
	 */
	public Brand createBrand(Brand brand);
	
	
	/**
	 * Retrieves all brand list
	 * @return
	 */
	public List<Brand> listAllBrand();
	
	
	
	/**
	 * Retrieves brand by ID
	 * @return
	 */
	public Brand getBrandById(Long id);
	
}
