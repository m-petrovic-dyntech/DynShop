package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
		
		Category firstCategory = new Category();
		firstCategory.setName("-- Select Category --");
		
		categories.add(firstCategory);
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
	
	
	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public ModelAndView cart(ModelAndView modelAndView) {
		
		modelAndView.setViewName("cart");

		return modelAndView;
	}
	
	@RequestMapping(value = { "products/add/{id}/{quantity}" }, method = RequestMethod.GET)
	public ModelAndView addProduct( @PathVariable (value = "id") int id, 
			@PathVariable (value = "quantity") int quantity, 
			@RequestParam(required = true) Integer categoryId,
			HttpSession session) {
		
		Product product = shoppingCartService.getProduct(id);
		ShoppingCartItem item = new ShoppingCartItem();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		System.out.println("BEFORE ADDING "+cart.getTotalCost());

		item.setProduct(product);
		item.setQuantity(quantity);
		item.total = quantity*product.getPrice();
		item.setTotal(item.getQuantity()*product.getPrice());

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
		
		items.add(item);
	
		cart.setItems(cart.getItems());
		cart.setTotalCost(cart.getTotalCost()+item.getTotal());
		
		session.setAttribute("cart", cart);
		System.out.println("AFTER ADDING "+cart.getTotalCost());
		
		if(categoryId == 0)
			return  new ModelAndView("redirect:/products");
		else 
			return  new ModelAndView("redirect:/products?category="+categoryId);
		
	}	
	
	@RequestMapping(value = { "products/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(
			@RequestParam(required = true) Integer categoryId,
			@PathVariable (value = "id") int id, 
			HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
		
		
		System.out.println("BEFORE REMOVING "+cart.getTotalCost());
				
		Iterator<ShoppingCartItem> itr = items.iterator();
	     while(itr.hasNext()) {
	    	  ShoppingCartItem element = (ShoppingCartItem) itr.next();
	         if(element.getProduct().getId() == id)
				{
					cart.setTotalCost(cart.getTotalCost()-element.getTotal());
					itr.remove();
				}
	      }
		 
		session.setAttribute("cart", cart);
		System.out.println("AFTER REMOVING"+cart.getTotalCost());

		if(categoryId == 0)
			return  new ModelAndView("redirect:/products");
		else 
			return  new ModelAndView("redirect:/products?category="+categoryId);
		
	}	
}
