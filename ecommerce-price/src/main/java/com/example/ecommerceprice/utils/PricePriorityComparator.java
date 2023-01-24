package com.example.ecommerceprice.utils;

import java.util.Comparator;

import com.example.ecommerceprice.entity.Price;

public class PricePriorityComparator implements Comparator<Price> { 
	
	 @Override
	    public int compare(Price firstPrice, Price secondPrice) {
	       return Integer.compare(firstPrice.getPriority(), secondPrice.getPriority());
	    }

}
