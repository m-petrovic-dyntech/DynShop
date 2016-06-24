package com.ShoppingCart.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.ShoppingCart;
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
	
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public ModelAndView getMail(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		//TODO get all product from delivery table with status pending
		
		modelAndView.setViewName("productForDelivery");
		return modelAndView;
	}
	
	//TODO REST poziv za get item iz korpe sa id_cart
	
	@RequestMapping(value = "/delivery/sentOrder", method = RequestMethod.GET)
	public ModelAndView sentProduct(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		//TODO change status of delivery to "sent"
		//sent mail to user
		//smanjujemo kolicnu prozvoda za rezervisano
		//reservisano umanjiti za broj prodatih
		modelAndView.setViewName("productForDelivery");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delivery/cancelOrder", method = RequestMethod.GET)
	public ModelAndView sentOrder(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		//TODO change status of delivery to "canceled"
		//sent mail to user
		//vratiti quantity prozvoda tj povecati 
		//reservisano umanjiti za broj cancelovanih
		modelAndView.setViewName("productForDelivery");
		return modelAndView;
	}
	
}
