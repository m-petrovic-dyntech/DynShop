package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
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
		
		for (ShoppingCart shoppingCart : carts) {
			shoppingCart.setItems(shoppingCartService.getItemsByCart(shoppingCart));
		}
		
		modelAndView.addObject("carts", carts);
		modelAndView.setViewName("cart_log");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/products" }, method = RequestMethod.GET)
	public ModelAndView adminGetProducts(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		
		modelAndView.addObject("products", shoppingCartService.getProducts(null));
		modelAndView.setViewName("admin_panel_products");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/users" }, method = RequestMethod.GET)
	public ModelAndView adminGetUsers(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		
		modelAndView.addObject("customers", customerService.getAllCustomers());
		modelAndView.setViewName("admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/categories" }, method = RequestMethod.GET)
	public ModelAndView adminCategories(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		
		modelAndView.addObject("categories", shoppingCartService.getCategories());
		modelAndView.setViewName("admin_panel_categories");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteCustomer/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCustomer(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		
		customerService.disableCustomer(customerService.getCustomerById(id));
		
		modelAndView.setViewName("redirect:admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteProduct/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteProduct(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		
		shoppingCartService.disableProduct(shoppingCartService.getProduct(id));
		
		modelAndView.setViewName("redirect:admin/panel/products");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteCategory/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCategory(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		shoppingCartService.disableCategory(shoppingCartService.getCategoryById(id));	
		
		modelAndView.setViewName("redirect:admin/panel/categories");
		return modelAndView;
	}
	
	/*
	@RequestMapping(value = { "/admin/panel/editProduct/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminEditProduct(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		
		Product product = shoppingCartService.getProduct(id);
		shoppingCartService.editProduct(product);
		
		modelAndView.setViewName("admin_panel_users");
		return modelAndView;
	}
 	*/
}
