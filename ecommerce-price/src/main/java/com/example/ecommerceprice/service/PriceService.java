package com.example.ecommerceprice.service;



import java.util.Date;
import java.util.List;

import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.exceptions.EcommercePriceException;

public interface PriceService {
	
	/**
	 * Retrieves all prices
	 * @return
	 */
	public List<Price> getAllPrices() throws EcommercePriceException;
	
	
	
	/**
	 * Retrieve a product prices by brand and date
	 * @return
	 */
	public Price getProductPriceByBrandAndDate(Long productId, Long brandId, Date date) throws EcommercePriceException;
	
}
