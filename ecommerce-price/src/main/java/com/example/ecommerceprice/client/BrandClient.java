package com.example.ecommerceprice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ecommerceprice.modelo.Brand;

@FeignClient(name ="ecommerce-brand",path="/api/brand",fallback = BrandHystrixFallBackFactory.class)
public interface BrandClient {
	
	
	/**
	 * Get a {@link Brand} by Id
	 * @param brandId
	 * @return
	 */
	@GetMapping(value = "/brandId/{brandId}")
	public ResponseEntity<Brand> getBrandById(@PathVariable("brandId") Long brandId);

}
