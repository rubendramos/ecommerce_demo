package com.example.ecommercebrand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ecommercebrand.entity.Brand;
import com.example.ecommercebrand.repository.BrandRepository;
import com.example.ecommercebrand.service.BrandService;
import com.example.ecommercebrand.service.BrandServiceImpl;

@SpringBootTest
class EcommerceBrandServiceMockTest {

	@Mock
	private BrandRepository addresRepository;

	private BrandService brandService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		brandService = new BrandServiceImpl(addresRepository);

		Brand testBrand = Brand.builder().id(1L).description("ZARA").build();

		Mockito.when(addresRepository.findById(1L)).thenReturn(Optional.of(testBrand));

		Mockito.when(addresRepository.save(testBrand)).thenReturn(testBrand);

	}

	@Test
	void whenValidGetId_thenReturnbrand() {
		Brand expectedBrand = Brand.builder().id(1L).description("ZARA").build();
		Brand brandFound = brandService.getBrandById(1L);
		assertThat(Objects.equals(brandFound, expectedBrand)).isTrue();
	}

}
