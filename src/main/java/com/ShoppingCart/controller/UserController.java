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

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class UserController extends ControllerUtil {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView getCartsByCustomer(ModelAndView modelAndView, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);

		Customer customer = (Customer) customerService.getCustomerById(getAuthenticatedUser().getId());
		List<ShoppingCart> carts = shoppingCartService.getCartsByCustomer(customer, page, size);

		modelAndView.addObject("history", carts);
		modelAndView.addObject("counter", shoppingCartService.getCountCartsInCustomer(customer));
		modelAndView.setViewName("history");

//		System.out.println("***************** " + shoppingCartService.getCountCartsInCustomer(customer));
		return modelAndView;
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
	public ModelAndView getItemsByCartId(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
			HttpSession session) {
		initializeSession(session);

		Customer customer = (Customer) customerService.getCustomerById(getAuthenticatedUser().getId());
		List<ShoppingCart> carts = (List<ShoppingCart>) shoppingCartService.getCartsByCustomer(customer, page, size);
		ShoppingCart cart = null;

		for (ShoppingCart shoppingCart : carts) {
			if (shoppingCart.getId() == id) {
				cart = shoppingCart;
				break;
			}
		}
		if (cart != null) {
			List<ShoppingCartItem> items = (List<ShoppingCartItem>) shoppingCartService.getItemsByCart(cart, page,
					size);
			
			
			modelAndView.addObject("history", items);
			modelAndView.addObject("counter", shoppingCartService.getCountItemsInCart(id));
			modelAndView.setViewName("history");
			System.out.println("***************** " + shoppingCartService.getCountItemsInCart(id));

			return modelAndView;
		} else 
			return new ModelAndView("redirect:/products");

	}

}
