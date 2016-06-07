package com.ShoppingCart.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Product;

@Controller
@RequestMapping(path = "/")
public class HomeController {

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		
		

		modelAndView.setViewName("index");
		
		
		
		return modelAndView;
	}

}

