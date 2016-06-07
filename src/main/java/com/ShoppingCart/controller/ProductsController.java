package com.ShoppingCart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Product;
import com.ShoppingCart.service.ShoppingCartService;

@Controller
public class ProductsController {

	@Autowired 
	ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		
		ArrayList<Product> products = shoppingCartService.getProducts();//new ArrayList<>();
				
		modelAndView.setViewName("products");
	    modelAndView.addObject("products", products);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/products/{id}" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView, @PathVariable (value = "id") int id) {
		
		Product product = shoppingCartService.getProduct(id);//new ArrayList<>();
		
		modelAndView.setViewName("products");
	    modelAndView.addObject("product", product);
		
		return modelAndView;
	}

}
