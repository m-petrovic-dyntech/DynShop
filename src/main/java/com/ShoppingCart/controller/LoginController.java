package com.ShoppingCart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = { "/userLogin" }, method = RequestMethod.GET)
	public ModelAndView userLogin(ModelAndView modelAndView) {
		
		modelAndView.setViewName("login");		
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/userRegister" }, method = RequestMethod.GET)
	public ModelAndView userRegister(ModelAndView modelAndView) {
		
		modelAndView.setViewName("register");	
	
		return modelAndView;
	}
}
