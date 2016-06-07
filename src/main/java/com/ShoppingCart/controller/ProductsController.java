package com.ShoppingCart.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Product;

@Controller
public class ProductsController {

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		
		ArrayList<Product> products = new ArrayList<>();
		
		for (int i = 0; i < 10; i++){
			Product product = new Product();

			product.setId(i);
			product.setDescription("Lorem ipsum" + i);
			product.setPrice(100.0 + i);
			
			products.add(product);
		}

		modelAndView.setViewName("products");
		
		modelAndView.addObject("products", products);
		
		return modelAndView;
	}

}
