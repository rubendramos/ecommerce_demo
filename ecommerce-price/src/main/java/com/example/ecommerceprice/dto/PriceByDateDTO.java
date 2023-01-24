package com.example.ecommerceprice.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;

import com.example.ecommerceprice.entity.enums.BrandEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PriceByDateDTO {
	
	@NotNull(message = "ProductId  is mandatory")
	private Long producId;
	
	@NotNull(message = "BrandEnum  is mandatory")
	private BrandEnum brandId;	
	
	@NotNull(message = "Date  is mandatory")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date efectiveDate;
}
