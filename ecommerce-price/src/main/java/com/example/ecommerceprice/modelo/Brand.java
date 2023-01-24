package com.example.ecommerceprice.modelo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand implements Serializable {


	private static final long serialVersionUID = 8266511525375268860L;

	
    private Long id;
    private String description;
    	
}
