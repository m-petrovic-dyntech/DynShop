package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.ShoppingCartService;

@Controller
public class CartController {

	@Autowired 
	ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public ModelAndView cart(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		modelAndView.setViewName("cart");
		modelAndView.addObject("cart", session.getAttribute("cart"));
		return modelAndView;
	}

	@RequestMapping(value = { "/cart/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable(value = "id") int id, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		//Testing
		//System.out.println("BEFORE REMOVING " + cart.getTotalCost());

		Iterator<ShoppingCartItem> itr = items.iterator();
		while (itr.hasNext()) {
			ShoppingCartItem element = (ShoppingCartItem) itr.next();
			if (element.getProduct().getId() == id) {
				cart.setTotalCost(cart.getTotalCost() - element.getTotal());
				itr.remove();
			}
		}
		
		session.setAttribute("cart", cart);
		//Testing
		//System.out.println("AFTER REMOVING" + cart.getTotalCost());
		return new ModelAndView("redirect:/cart");

	}

	@RequestMapping(value = { "/cart/deleteAll" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpSession session) {
		initializeSession(session);
		session.setAttribute("cart", new ShoppingCart());
		return new ModelAndView("redirect:/products");
	}

	@RequestMapping(value = { "/cart/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView changeQuantityOfItem(@PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer value, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		//Testing
		//System.out.println("BEFORE EDIT QUANTITY " + cart.getTotalCost());

		if (cart.findItemByProductId(id) != null) {
			cart.findItemByProductId(id).setQuantity(value);
			Double currentTotal = cart.getTotalCost() - cart.findItemByProductId(id).getTotal();
			cart.findItemByProductId(id).setTotal(
					cart.findItemByProductId(id).getQuantity() * cart.findItemByProductId(id).getProduct().getPrice());

			cart.setTotalCost(currentTotal + cart.findItemByProductId(id).getTotal());
		}
		//Testing
		//System.out.println("AFTER EDIT QUANTITY " + cart.getTotalCost());

		return new ModelAndView("redirect:/cart");
	}
	
	@RequestMapping(value = { "/cartSave" }, method = RequestMethod.GET)
	public ModelAndView cartSave(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		shoppingCartService.saveCart(cart);
		
		session.setAttribute("cart", new ShoppingCart());
		modelAndView.addObject("cart", cart);
		
		modelAndView.setViewName("redirect: products");
		return modelAndView;
	}
	
	private void initializeSession(HttpSession session) {
		if(session.getAttribute("cart") == null)
			session.setAttribute("cart", new ShoppingCart());
	}
}
