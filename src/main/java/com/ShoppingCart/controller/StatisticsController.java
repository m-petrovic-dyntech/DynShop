package com.ShoppingCart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class StatisticsController extends ControllerUtil{
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	/**
	 *  average of all purcashed carts in one month
	 */
	@RequestMapping(value = { "/statistics/monthlyAverage/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminGetCartsLogs(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			HttpSession session) {
		initializeSession(session);

		List<ShoppingCart> carts = shoppingCartService.getAllCartsPurcashedByMonth(id);
		//ovde Vlada operira :) 

		modelAndView.addObject("carts", carts);
		modelAndView.setViewName("cart_log");
		return modelAndView;
	}

}
