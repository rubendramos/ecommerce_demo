package com.example.ecommerceprice.entity.enums;

import java.util.stream.Stream;


public enum BrandEnum {
	HOME(0L),
	ZARA(1L);

	private Long brandId;

	private BrandEnum(Long brandId) {
		this.brandId = brandId;
	}


	public Long getBrandId() {
		return this.brandId;
	}

	
	public static BrandEnum of(Long brand) {
		return Stream.of(BrandEnum.values()).filter(p -> p.getBrandId() == brand).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
