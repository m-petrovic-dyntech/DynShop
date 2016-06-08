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
	public ModelAndView home(ModelAndView modelAndView) {
		
		ArrayList<Product> products = shoppingCartService.getProducts();
		ArrayList<Category> categories = shoppingCartService.getCategories();
		Category category = new Category();
		Category cat = shoppingCartService.getCategoryById(new Integer(4));

		modelAndView.setViewName("products");
		
	    modelAndView.addObject("products", products);
	    modelAndView.addObject("categories", categories);
	    modelAndView.addObject("category", category);
	    logger.debug("************" + Arrays.toString(cat.getProducts().toArray()) + "************");
		return modelAndView;
	}
	
	/*@RequestMapping(value = { "/products" }, method = RequestMethod.POST)
	public ModelAndView getByCategory(@ModelAttribute("category")  Category category, ModelAndView modelAndView) {
		
		ArrayList<Product> sortedProducts = shoppingCartService.getProductsByCategory(category.getId());


		
		modelAndView.setViewName("products");
		modelAndView.addObject("products", sortedProducts);
		
		return modelAndView;
	}*/
	
	@RequestMapping(value = { "/products/{id}" }, method = RequestMethod.GET)
	public ModelAndView getProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id) {
		
		Product product = shoppingCartService.getProduct(id);//new ArrayList<>();
				
		System.out.println("******"+product.getName()+" u kategoriji "+product.getCategory().getName());
		
		modelAndView.setViewName("products");
	    modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/add/{id}" }, method = RequestMethod.GET)
	public ModelAndView addProduct(ModelAndView modelAndView, @PathVariable (value = "id") int id, 
			HttpSession session) {
		
		Product product = shoppingCartService.getProduct(id);
		ShoppingCartItem item = new ShoppingCartItem();
		item.setProduct(product);
		item.setQuantity(4);
		item.setTotal(item.getQuantity()*product.getPrice());
		
		ArrayList<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		items.add(item);
		ShoppingCart janinaKolica = new ShoppingCart();
		janinaKolica.setItems(items);
		janinaKolica.setTotalCost(55.0);
		
		session.setAttribute("cart", janinaKolica);
		System.out.println("****"+session.getAttribute("cart"));
		
		return modelAndView;
	}	
}
