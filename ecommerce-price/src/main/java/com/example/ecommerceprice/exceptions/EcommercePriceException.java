package com.example.ecommerceprice.exceptions;

public class EcommercePriceException extends BaseException{


	private static final long serialVersionUID = 3279279918225869344L;
	private static final String MESSAGE_EXCEPTION = "Ecommerce price generic exception";

	
	public EcommercePriceException(Exception exception, Object[] paramsException) {
		super(exception, paramsException);		
	}
	
	
	public EcommercePriceException(String message, Object[] paramsException) {
		super(message, paramsException);		
	}
	
	public EcommercePriceException(Object[] paramsException) {
		super(MESSAGE_EXCEPTION, paramsException);
	}

}
