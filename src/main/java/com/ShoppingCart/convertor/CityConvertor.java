package com.ShoppingCart.convertor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ShoppingCart.entity.City;
import com.ShoppingCart.service.CustomerService;

public class CityConvertor implements Converter<Integer, City> {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public City convert(Integer source) {
		return customerService.getCityById(source);
		
	}

}
