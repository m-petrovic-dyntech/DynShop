package com.ShoppingCart.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.util.ControllerUtil;

@Controller
public class ForgottenPasswordController extends ControllerUtil {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = { "/forgottenPassword" }, method = RequestMethod.GET)
	public ModelAndView forgottenPassword(ModelAndView modelAndView) {
		

		

		
		modelAndView.setViewName("forgotten_password");
		return modelAndView;
	}

	@RequestMapping(value = { "/changeForgottenPassword" }, method = RequestMethod.POST)
	public ModelAndView changeForgottenPassword(ModelAndView modelAndView) {
		

		

		
		modelAndView.setViewName("redirect:forgottenPassword");
		return modelAndView;
	}
	
}
