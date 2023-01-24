package com.example.ecommerceprice.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerceprice.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long>{

	/**
	 * Retrieves Price entity from by currency
	 * @param Price
	 * @return
	 */
	public List<Price> findBycurrency(String currency);
	
	/**
	 * Retrive Price by producId, brandId and date
	 * @param productId
	 * @param brandId
	 * @param date
	 * @return
	 */
	@Query("SELECT price FROM Price price WHERE price.priceId.productId = ?1 and price.priceId.brandId = ?2 and price.startDate <= ?3 and price.endDate >= ?3")
	public List<Price> findPriceByPriceId(Long productId, Long brandId, Date date);
	

}
