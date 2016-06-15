package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class AdminController extends ControllerUtil {
	
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
		
	@RequestMapping(value = { "/admin/cartLogs" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		
		List<ShoppingCart> carts = shoppingCartService.getAllCarts();
		
		modelAndView.addObject("carts", carts);
		modelAndView.setViewName("cart_log");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/products" }, method = RequestMethod.GET)
	public ModelAndView adminGetProducts(ModelAndView modelAndView, HttpSession session) {
				
		modelAndView.setViewName("admin_panel_products");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/users" }, method = RequestMethod.GET)
	public ModelAndView adminGetUsers(ModelAndView modelAndView, HttpSession session) {
				
		modelAndView.setViewName("admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/categories" }, method = RequestMethod.GET)
	public ModelAndView adminCategories(ModelAndView modelAndView, HttpSession session) {
				
		modelAndView.setViewName("admin_panel_categories");
		return modelAndView;
	}

}
