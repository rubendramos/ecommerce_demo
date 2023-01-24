package com.example.ecommerceprice.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.example.ecommerceprice.dto.PriceDTO;
import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.entity.PriceId;
import com.example.ecommerceprice.modelo.Brand;

public class PriceMapper {

	
	private static final  SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	
	
	/**
	 * Convert Price Entity in a priceDTO 
	 * @param price
	 * @return
	 */
	public static PriceDTO convertToDTO(Price price) {
		return parsePriceToPriceDTO(price);
	}
	
	
	/**
	 * Convert a priceDTO in a Price Entity
	 * @param priceDTO
	 * @return
	 */
	public static Price convertToEntity(PriceDTO priceDTO) {
		return parsePriceDTOToPrice(priceDTO);
	}
	
	/**
	 * Convert a priceDTO in a price Entity
	 * @param priceDTO
	 * @return
	 */
	private static Price parsePriceDTOToPrice(PriceDTO priceDTO) {
		
		PriceId priceId = PriceId.builder()
				.brand(Brand.builder().description(priceDTO.getBrandId()).build())
				.priceList(priceDTO.getPriceList())
				.productId(priceDTO.getProducId())
				.build();
		
		return Price.builder().priceId(priceId).build();
				

	}


	/**
	 *  Convert Price Entity in a PriceDTO 
	 * @param price
	 * @return
	 */
	private static PriceDTO parsePriceToPriceDTO(Price price) {
		
		return PriceDTO.builder()
				.brandId(price.getPriceId().getBrand().getDescription())
				.endDate(formatDate(price.getEndDate()))
				.startDate(formatDate(price.getStartDate()))
				.producId(price.getPriceId().getProductId())
				.priceList(price.getPriceId().getPriceList())
				.price(price.getPrice()).build();
	}
	
	/**
	 * Set format date to timezone 
	 * @param date
	 * @return
	 */
	private static Date formatDate(Date date) {
		try {
			DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
			return DATE_TIME_FORMAT.parse(date.toString());
		}catch(ParseException e) {
			return date;
		}
		
		
	}

}
