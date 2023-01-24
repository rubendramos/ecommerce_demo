package com.example.ecommerceprice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.example.ecommerceprice.modelo.Brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
@Getter
public class PriceId implements Serializable {

	private static final long serialVersionUID = 8266511525375268860L;

	@Transient
	private Brand brand;

	@Column(name = "BRAND_ID", nullable = false)
	private Long brandId;

	@NotNull(message = "Product_is is mandatory")
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "PRICE_LIST", nullable = false)
	private Long priceList;

}
