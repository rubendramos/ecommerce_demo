package com.example.ecommercebrand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ecommercebrand.entity.Brand;
import com.example.ecommercebrand.repository.BrandRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EcommerceBrandRepositoryTest {
	
	
	@Autowired
	private BrandRepository brandRepository;
		

	@Test
	void whenFindBybrandByExistingId_thenReturnsABrand() {
		
		Brand dbBrand = null;
		Brand testBrand = Brand.builder().id(1L).description("ZARA").build();
		
		Optional<Brand> value = brandRepository.findById(1l);
		if(value.isPresent()) {
			dbBrand = value.get();
		}
		
		assertThat(Objects.equals(dbBrand, testBrand)).isTrue();
	}

	
}
