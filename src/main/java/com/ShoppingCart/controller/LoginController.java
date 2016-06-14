package com.ShoppingCart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value = { "/userLogin" }, method = RequestMethod.GET)
	public ModelAndView cart(ModelAndView modelAndView, HttpSession session) {
		
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
}
