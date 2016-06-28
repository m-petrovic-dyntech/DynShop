package com.ShoppingCart.controller;

import java.util.ArrayList;
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

import com.ShoppingCart.dto.JtoPagination;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.MailService;
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

	@Autowired
	private MailService mailService;

	@RequestMapping(value = { "/admin/cartLogs" }, method = RequestMethod.GET)
	public ModelAndView adminGetCartsLogs(ModelAndView modelAndView, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);

		List<ShoppingCart> carts = shoppingCartService.getAllCarts(page, size);

		for (ShoppingCart shoppingCart : carts) {
			shoppingCart.setItems(shoppingCartService.getItemsByCart(shoppingCart, page, size));
		}

		JtoPagination pagination = new JtoPagination(page, size, shoppingCartService.getCountCarts());

		modelAndView.addObject("carts", carts);
		modelAndView.addObject("pagination", pagination);
		modelAndView.setViewName("cart_log");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/products" }, method = RequestMethod.GET)
	public ModelAndView adminGetProductsByCategory(ModelAndView modelAndView,
			@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);

		if (categoryId == null || categoryId == 0) {
			JtoPagination pagination = new JtoPagination(page, size, shoppingCartService.getCountProducts());
			modelAndView.addObject("products", shoppingCartService.getProducts(null, page, size));
			modelAndView.addObject("pagination", pagination);
			// System.out.println("************ " + pagination.toString());
		} else {
			JtoPagination pagination = new JtoPagination(page, size,
					shoppingCartService.getCountProductsInCategory(categoryId));
			modelAndView.addObject("products",
					shoppingCartService.getProducts(shoppingCartService.getCategoryById(categoryId), page, size));
			modelAndView.addObject("pagination", pagination);
			// System.out.println("************ " + pagination.toString());

		}
		modelAndView.addObject("categories", shoppingCartService.getCategories(page, size));
		modelAndView.setViewName("admin_panel_products");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/categories" }, method = RequestMethod.GET)
	public ModelAndView adminCategories(ModelAndView modelAndView, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);
		JtoPagination pagination = new JtoPagination(page, size, shoppingCartService.getCountCategories());
		modelAndView.addObject("categories", shoppingCartService.getCategories(page, size));
		modelAndView.addObject("pagination", pagination);
		System.out.println("*************** " + pagination.toString());

		modelAndView.setViewName("admin_panel_categories");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/deleteProduct/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteProduct(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			HttpSession session) {
		initializeSession(session);

		Product product = (Product) shoppingCartService.getProductById(id);
		product.setEnabled(Boolean.FALSE);

		shoppingCartService.editProduct(product);

		modelAndView.setViewName("redirect:/admin/panel/products");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/deleteCategory/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCategory(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			HttpSession session) {
		initializeSession(session);

		Category category = (Category) shoppingCartService.getCategoryById(id);
		category.setEnabled(Boolean.FALSE);

		shoppingCartService.editCategory(category);

		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/editCategory" }, method = RequestMethod.GET)
	public ModelAndView adminEditCategory(ModelAndView modelAndView, @RequestParam("id") int id,
			@RequestParam("name") String name, @RequestParam("enabled") Boolean enabled, HttpSession session) {

		Category category = (Category) shoppingCartService.getCategoryById(id);
		category.setName(name);
		category.setEnabled(enabled);

		shoppingCartService.editCategory(category);

		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/editProduct" }, method = RequestMethod.GET)
	public ModelAndView adminEditProduct(ModelAndView modelAndView, @ModelAttribute("productEditModel") Product product,
			HttpSession session) {

		shoppingCartService.editProduct(product);

		modelAndView.setViewName("redirect:/admin/panel/product");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/addProduct" }, method = RequestMethod.GET)
	public ModelAndView adminAddProduct(ModelAndView modelAndView, @ModelAttribute("productAddModel") Product product,
			HttpSession session) {

		shoppingCartService.addProduct(product);
		modelAndView.setViewName("redirect:/admin/panel/product");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/addCategory" }, method = RequestMethod.GET)
	public ModelAndView adminAddCategory(ModelAndView modelAndView,
			@ModelAttribute("categoryAddModel") Category category, HttpSession session) {

		shoppingCartService.addCategory(category);
		modelAndView.setViewName("redirect:/admin/panel/categories");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/pendingCarts" }, method = RequestMethod.GET)
	public ModelAndView adminPendingCarts(ModelAndView modelAndView, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);

		JtoPagination pagination = new JtoPagination(page, size, shoppingCartService.getCountPandingCarts());
		modelAndView.addObject("carts", shoppingCartService.getPendingCarts(page, size));
		modelAndView.addObject("pagination", pagination);

		modelAndView.setViewName("admin_panel_pending_carts");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/changeCartStatus/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminChangeCartStatus(ModelAndView modelAndView, HttpSession session,
			@PathVariable(value = "id") int id, @RequestParam(required = true) String status) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) shoppingCartService.getCartById(id);
		Delivery delivery = new Delivery();

		if (status.equals("pending_delivery")) {
			delivery.setStatus("pending");
			delivery.setNote("administrator je odobrio porudzbinu");
			shoppingCartService.addDelivery(delivery);
			cart.setDelivery(delivery);
		}
		List<ShoppingCartItem> cartItems = (List<ShoppingCartItem>) shoppingCartService.getItemsByCart(cart, null,
				null);
		cart.setStatus(status);
		shoppingCartService.editCart(cart);
		List<String> downloadLinks = new ArrayList<String>();

		for (ShoppingCartItem item : cartItems) {
			if ((shoppingCartService.getProductById(item.getProduct().getId()).getProductType()).equals(0))
				downloadLinks.add(shoppingCartService.getProductById(item.getProduct().getId()).getDownloadLink());
		}
		String template = "confirmShoppingOnlyDownloadTemplate.vm";
		if (cartItems.size() > downloadLinks.size()) {
			template = "confirmShoppingTemplate.vm";
		}
		mailService.sendConfirmShoppingMail(customerService.getCustomerById(getAuthenticatedUser().getId()),
				"info@dyntechshop.com", "j.dumeljic@dyntechdoo.com", "Vasa kupovina je uspesno obavljena",
				downloadLinks, template);

		modelAndView.setViewName("redirect:/admin/panel/pendingCarts");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/deliveries" }, method = RequestMethod.GET)
	public ModelAndView adminDelivery(ModelAndView modelAndView, @RequestParam(required = false) String status, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {
		initializeSession(session);
		JtoPagination pagination;
		
		if(status == null)
			pagination = new JtoPagination(page, size, shoppingCartService.getCountDeliveryCarts());
		else 
			pagination = new JtoPagination(page, size, shoppingCartService.getCountDeliveryCarts(status));
		
		System.out.println("*****************   " + pagination);

		modelAndView.addObject("carts", shoppingCartService.getDeliveryCarts(status, page, size));
		modelAndView.addObject("pagination", pagination);
		modelAndView.setViewName("admin_panel_deliveries");
		return modelAndView;
	}
	
}
