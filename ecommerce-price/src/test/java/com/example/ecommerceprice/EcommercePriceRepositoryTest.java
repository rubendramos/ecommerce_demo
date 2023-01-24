package com.example.ecommerceprice;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.repository.PriceRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EcommercePriceRepositoryTest {
	
	@Autowired
	private PriceRepository priceRepository;
	
	private static final int NUMBER_TOTAL_PRICES = 4;

	@Test
	void whenFindAllPrices_thenReturnsAListOfPrices() {
		
		List<Price> priceList = priceRepository.findAll();		
		Assertions.assertThat(priceList).hasSize(NUMBER_TOTAL_PRICES);

	}
}
