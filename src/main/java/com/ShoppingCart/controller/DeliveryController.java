package com.ShoppingCart.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class DeliveryController  extends ControllerUtil{

	
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CustomerService customerService;
	
	//todo add new products(change quantity)
	//remove product decrease quantity
	
	
}
