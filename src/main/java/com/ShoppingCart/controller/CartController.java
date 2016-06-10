package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.ShoppingCart.entity.ShoppingCartItem;

@Controller
public class CartController {

	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public ModelAndView cart(ModelAndView modelAndView) {
		
		modelAndView.setViewName("cart");

		return modelAndView;
	}
	
	@RequestMapping(value = { "/cart/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable(value = "id") int id, HttpSession session) {

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
		return new ModelAndView("redirect:/cart");
		
	}
	
	@RequestMapping(value = { "/cart/deleteAll" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpSession session) {

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		System.out.println("BEFORE DELETING ALL " + cart.getTotalCost());
		
		items.clear();
	    cart.setItems(items);
	    cart.setTotalCost(0.0);

		session.setAttribute("cart", cart);
		System.out.println("AFTER  DELETING ALL " + cart.getTotalCost());
		return new ModelAndView("redirect:/cart");
	}
	
	@RequestMapping(value = { "/cart/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView changeQuantityOfItem(@PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer value, HttpSession session) {

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		System.out.println("BEFORE EDIT QUANTITY " + cart.getTotalCost());

		if(cart.findItemByProductId(id) != null) {
			cart.findItemByProductId(id).setQuantity(value);
			Double currentTotal = cart.getTotalCost() - cart.findItemByProductId(id).getTotal();
			cart.findItemByProductId(id).setTotal(cart.findItemByProductId(id).getQuantity() * cart.findItemByProductId(id).getProduct().getPrice());
			
			cart.setTotalCost(currentTotal + cart.findItemByProductId(id).getTotal());
		}
		System.out.println("AFTER EDIT QUANTITY " + cart.getTotalCost());

		return new ModelAndView("redirect:/cart");
		
	}
}
