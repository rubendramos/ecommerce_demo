package com.example.ecommercebrand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommercebrand.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

	
	/**
	 *  Retrieves brand entity by id 
	 */
	public Optional<Brand>  findById(Long id);
}
