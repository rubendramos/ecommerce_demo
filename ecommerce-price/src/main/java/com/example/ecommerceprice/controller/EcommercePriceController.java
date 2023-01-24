package com.example.ecommerceprice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceprice.dto.PriceByDateDTO;
import com.example.ecommerceprice.dto.PriceDTO;
import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.exceptions.EcommercePriceException;
import com.example.ecommerceprice.mappers.PriceMapper;
import com.example.ecommerceprice.service.PriceService;

@RestController
@RequestMapping(value = "api/price")
public class EcommercePriceController {

	Logger logger = LoggerFactory.getLogger(EcommercePriceController.class);
	

	@Autowired
	private PriceService priceService;
	
	
	/**
	 * Retrieve all messages
	 * @return
	 */
	@GetMapping("/allPrices")
	public ResponseEntity<List<Price>> listPrices() throws EcommercePriceException{
		
		List<Price> messageList = priceService.getAllPrices();
		if (!messageList.isEmpty()) {
			return ResponseEntity.ok(messageList);
		}
		return ResponseEntity.noContent().build();

	}
	
	
	@GetMapping
	public ResponseEntity<PriceDTO> searchPriceByDate(@RequestBody @Valid PriceByDateDTO priceByDateDTO) throws EcommercePriceException {
		Price price = null;
		
		price = priceService.getProductPriceByBrandAndDate(priceByDateDTO.getProducId(), priceByDateDTO.getBrandId().getBrandId(), priceByDateDTO.getEfectiveDate());

		if (price != null) {
			return ResponseEntity.ok(PriceMapper.convertToDTO(price));
		}
		
		return ResponseEntity.noContent().build();
	}

	

}
