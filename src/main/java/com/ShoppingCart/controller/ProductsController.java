package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.ShoppingCartService;

@Controller
//@Scope("session")
public class ProductsController {
	
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired 
	ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView, @RequestParam(required = false) Integer category, HttpSession session ) {
		
		if(session.getAttribute("cart") == null)
			session.setAttribute("cart", new ShoppingCart());
			
		List<Product> products = shoppingCartService.getProducts(category);
				
		Category selectedCategory = shoppingCartService.getCategoryById(category);
				
		ArrayList<Category> categories= new ArrayList<>();
		categories.add(selectedCategory);
		categories.addAll(shoppingCartService.getCategories());
				
		modelAndView.setViewName("products");
		
	    modelAndView.addObject("products", products);
	    
	    modelAndView.addObject("categories", categories);
	    modelAndView.addObject("category", selectedCategory);
	    modelAndView.addObject("cart", session.getAttribute("cart") );
	    
		return modelAndView;
	}
		
	@RequestMapping(value = { "/products/{id}" }, method = RequestMethod.GET)
	public ModelAndView getProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id) {
		
		Product product = shoppingCartService.getProduct(id);
				
		System.out.println("******"+product.getName()+" u kategoriji "+product.getCategory().getName());
		
		modelAndView.setViewName("products");
	    modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "product/add/{id}/{quantity}" }, method = RequestMethod.GET)
	public ModelAndView addProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id, 
			@PathVariable (value = "quantity") int quantity,
			HttpSession session) {
		
		Product product = shoppingCartService.getProduct(id);
		ShoppingCartItem item = new ShoppingCartItem();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		item.setProduct(product);
		item.setQuantity(quantity);
		item.total = quantity*product.getPrice();
		item.setTotal(item.getQuantity()*product.getPrice());

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
		
		items.add(item);
	
		cart.setItems(cart.getItems());
		cart.setTotalCost(cart.getTotalCost()+item.getTotal());
		
		session.setAttribute("cart", cart);
		System.out.println("****"+cart.getTotalCost());
		
		return modelAndView;
	}	
	
	@RequestMapping(value = { "product/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView addProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id, 
			HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
		
		
		System.out.println("BEFORE REMOVING "+cart.getTotalCost());
		
		for (ShoppingCartItem shoppingCartItem : items) {
			if(shoppingCartItem.getProduct().getId() == id)
			{
				cart.setTotalCost(cart.getTotalCost()-shoppingCartItem.getTotal());
				cart.getItems().remove(shoppingCartItem);
			}
		}
		
		session.setAttribute("cart", cart);
		System.out.println("AFTER REMOVING"+cart.getTotalCost());
		
		return modelAndView;
	}	
}
