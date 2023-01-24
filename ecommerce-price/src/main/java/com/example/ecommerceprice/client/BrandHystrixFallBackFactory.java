package com.example.ecommerceprice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.ecommerceprice.modelo.Brand;

@Component
public class BrandHystrixFallBackFactory implements BrandClient {

	@Override
	public ResponseEntity<Brand> getBrandById(Long brandId) {
		return  ResponseEntity.ok(getUnknownBrand());	}
	
	/**
	 * Retrieves a unknown Brand
	 * @return
	 */
	private Brand getUnknownBrand() {
		return  Brand.builder().description("Unknown").build();
	}

}
