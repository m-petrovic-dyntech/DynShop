package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

@Controller
public class CartController {

	@RequestMapping(value = { "products/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam(required = true) Integer categoryId,
			@PathVariable(value = "id") int id, HttpSession session) {

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		System.out.println("BEFORE REMOVING " + cart.getTotalCost());

		Iterator<ShoppingCartItem> itr = items.iterator();
		while (itr.hasNext()) {
			ShoppingCartItem element = (ShoppingCartItem) itr.next();
			if (element.getProduct().getId() == id) {
				cart.setTotalCost(cart.getTotalCost() - element.getTotal());
				itr.remove();
			}
		}

		session.setAttribute("cart", cart);
		System.out.println("AFTER REMOVING" + cart.getTotalCost());

		if (categoryId == 0)
			return new ModelAndView("redirect:/products");
		else
			return new ModelAndView("redirect:/products?category=" + categoryId);
	}
	
	@RequestMapping(value = { "products/deleteAll" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(
			HttpSession session) {

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		System.out.println("BEFORE DELETING ALL " + cart.getTotalCost());
		
		items.clear();
	    cart.setItems(items);
	    cart.setTotalCost(0.0);

		session.setAttribute("cart", cart);
		System.out.println("AFTER  DELETING ALL " + cart.getTotalCost());
		return new ModelAndView("redirect:/products");

	}

}
