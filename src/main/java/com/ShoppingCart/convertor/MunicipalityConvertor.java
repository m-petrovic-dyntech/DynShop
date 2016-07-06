package com.ShoppingCart.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ShoppingCart.entity.Municipality;
import com.ShoppingCart.service.CustomerService;

public class MunicipalityConvertor implements Converter<Integer, Municipality> {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public Municipality convert(Integer source) {
		return customerService.getMunicipalityById(source);
		
	}

}
