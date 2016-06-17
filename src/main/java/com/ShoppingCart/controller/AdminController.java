package com.ShoppingCart.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
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
		
		for (ShoppingCart shoppingCart : carts) {
			shoppingCart.setItems(shoppingCartService.getItemsByCart(shoppingCart));
		}
		
		modelAndView.addObject("carts", carts);
		modelAndView.setViewName("cart_log");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/products" }, method = RequestMethod.GET)
	public ModelAndView adminGetProductsByCategory(ModelAndView modelAndView, HttpSession session, 
		@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Integer page, 
		@RequestParam(required = false) Integer size) {
		initializeSession(session);
		
		//TODO doati page i size
		if(categoryId == null || categoryId == 0)	
			modelAndView.addObject("products", shoppingCartService.getProducts(null));
		else modelAndView.addObject("products", shoppingCartService.getProducts(shoppingCartService.getCategoryById(categoryId)));
		
		modelAndView.addObject("categories", shoppingCartService.getCategories());
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
	public ModelAndView adminCategories(ModelAndView modelAndView, HttpSession session,
			@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size) {
		initializeSession(session);
		
		modelAndView.addObject("paginatedCategories", getPaginatedList(shoppingCartService.getCategories(), page, size));

		modelAndView.addObject("categories", shoppingCartService.getCategories());
		modelAndView.setViewName("admin_panel_categories");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteCustomer/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCustomer(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		
		Customer customer = (Customer)customerService.getCustomerById(id);
		customer.setEnabled(Boolean.FALSE);
		
		customerService.editCustomer(customer);
		
		modelAndView.setViewName("redirect:/admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteProduct/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteProduct(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		
		Product product = (Product)shoppingCartService.getProduct(id);
		product.setEnabled(Boolean.FALSE);
		
		shoppingCartService.editProduct(product);
		
		modelAndView.setViewName("redirect:/admin/panel/products");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/deleteCategory/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCategory(ModelAndView modelAndView, HttpSession session,  @PathVariable (value = "id") int id ) {
		initializeSession(session);
		
		Category category = (Category)shoppingCartService.getCategoryById(id);
		category.setEnabled(Boolean.FALSE);
		
		shoppingCartService.editCategory(category);
		
		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/editCategory" }, method = RequestMethod.GET)
	public ModelAndView adminEditCategory(ModelAndView modelAndView, HttpSession session,  @RequestParam("id") int id, 
			@RequestParam("name") String name, @RequestParam("enabled") Boolean enabled) {
		
		Category category = (Category)shoppingCartService.getCategoryById(id);
		category.setName(name);
		category.setEnabled(enabled);
		
		shoppingCartService.editCategory(category);
		
		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/editProduct" }, method = RequestMethod.GET)
	public ModelAndView adminEditProduct(ModelAndView modelAndView, HttpSession session, @ModelAttribute("productEditModel") Product product) {
		
		shoppingCartService.editProduct(product);
		
		modelAndView.setViewName("redirect:/admin/panel/product");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/addProduct" }, method = RequestMethod.GET)
	public ModelAndView adminAddProduct(ModelAndView modelAndView, HttpSession session, @ModelAttribute("product") Product product) {
		
		shoppingCartService.addProduct(product);
		modelAndView.setViewName("redirect:/admin/panel/product");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/addCategory" }, method = RequestMethod.GET)
	public ModelAndView adminAddCategory(ModelAndView modelAndView, HttpSession session, @ModelAttribute("product") Category category) {
		
		shoppingCartService.addCategory(category);		
		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}
 	
}
