package com.example.ecommerceprice.exceptions;

public class NonUniquePriceListException extends EcommercePriceException{


	private static final long serialVersionUID = 3279279918225869344L;
	private static final String MESSAGE_EXCEPTION = "Retrieve Price list is not Unique. Imposible set price to product ";
	
	public NonUniquePriceListException(Exception exception, Object[] paramsException) {
		super(exception, paramsException);		
	}
	
	
	public NonUniquePriceListException(String message, Object[] paramsException) {
		super(message, paramsException);		
	}
	

	
	public NonUniquePriceListException(Object[] paramsException) {
		super(MESSAGE_EXCEPTION, paramsException);
	}	

}
