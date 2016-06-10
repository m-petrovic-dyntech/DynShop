package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
	@RequestMapping(value = { "/product/{id}" }, method = RequestMethod.GET)
	public ModelAndView getProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id,
			@RequestParam(required = true) Integer categoryId) {
		
		Product product = shoppingCartService.getProduct(id);
				
		System.out.println("******"+product.getName()+" u kategoriji "+product.getCategory().getName());
		
		modelAndView.setViewName("product");
	    modelAndView.addObject("product", product);
	    modelAndView.addObject("categoryId", categoryId);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "product/add/{id}/{quantity}" }, method = RequestMethod.GET)
	public ModelAndView addProductToCart( @PathVariable (value = "id") int id, 
			@PathVariable (value = "quantity") int quantity, 
			@RequestParam(required = true) Integer categoryId,
			HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
				
		System.out.println("BEFORE ADDING "+cart.getTotalCost());

		if(cart.findItemByProductId(id) == null)
		{
			Product product = shoppingCartService.getProduct(id);
			
			ShoppingCartItem item = new ShoppingCartItem();
			item.setProduct(product);
			item.setQuantity(quantity);
			item.total = quantity*product.getPrice();
			item.setTotal(item.getQuantity()*product.getPrice());
	
			items.add(item);
		}
		//edit existing ShoppingCartItem quantity
		else{
			cart.setTotalCost(cart.getTotalCost()-cart.findItemByProductId(id).getTotal());
		    cart.findItemByProductId(id).setQuantity(cart.findItemByProductId(id).getQuantity()+quantity);
		    cart.findItemByProductId(id).setTotal(cart.findItemByProductId(id).getQuantity()*cart.findItemByProductId(id).getProduct().getPrice());
		    items.set(items.indexOf(cart.findItemByProductId(id)), cart.findItemByProductId(id));  
		}
		
		cart.setTotalCost(cart.getTotalCost()+cart.findItemByProductId(id).getTotal());
		cart.setItems(items);
		session.setAttribute("cart", cart);
		System.out.println("AFTER ADDING "+cart.getTotalCost());
		
		if(categoryId == 0)
			return  new ModelAndView("redirect:/products");
		else 
			return  new ModelAndView("redirect:/products?category="+categoryId);
		
	}	
}
