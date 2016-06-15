package com.ShoppingCart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.util.ControllerUtil;

@RequestMapping("/user")
@Controller
public class UserController extends ControllerUtil {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView getCartsByCustomerId(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);

		Customer customer = (Customer) customerService.getCustomerById(getAuthenticatedUser().getId());
		List<ShoppingCart> carts = customerService.getCartsByCustomer(customer);

		modelAndView.setViewName("history");
		modelAndView.addObject("history", carts);
		return modelAndView;
	}
}
