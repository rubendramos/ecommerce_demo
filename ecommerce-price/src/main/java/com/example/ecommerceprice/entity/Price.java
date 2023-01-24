package com.example.ecommerceprice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRICE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price implements Serializable {

	private static final long serialVersionUID = 8266511525375268860L;

	@EmbeddedId
    private PriceId priceId;
	
	@NotEmpty(message = "Start date is mandatory")
	@Column(name="START_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name="END_DATE", nullable=false)
	@NotEmpty(message = "End date is mandatory")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name="PRICE", nullable=false)
	@NotEmpty(message = "Price is mandatory")
	private Double price;

	@NotEmpty(message = "Currency is mandatory")
	@Column(name="CURR", nullable=false)
	private String currency;
	
	@Column(name="PRIORITY", nullable=false)
    private int priority;
	
}
