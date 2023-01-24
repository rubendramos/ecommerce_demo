package com.example.ecommercebrand.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommercebrand.entity.Brand;
import com.example.ecommercebrand.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepositoty;
	

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepositoty.save(brand);
	}
	
	@Override
	public List<Brand> listAllBrand() {
		return brandRepositoty.findAll();
	}



	@Override
	public Brand getBrandById(Long id) {
		Brand brand = null;
		
		Optional<Brand> value = brandRepositoty.findById(id);
		if(value.isPresent()) {
			brand = value.get();
		}
		
		return  brand;
	}

	

	
}
