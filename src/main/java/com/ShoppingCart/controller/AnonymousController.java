package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class AnonymousController extends ControllerUtil {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userLogin(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);

		modelAndView.addObject("customer", new Customer());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView userRegister(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = { "/products", "admin/products", "/" }, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView, @RequestParam(required = false) Integer category,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
			HttpSession session) {
		initializeSession(session);

		Category selectedCategory = new Category();
		int counter;

		if (category != null && category != 0) {
			selectedCategory = shoppingCartService.getCategoryById(category);
			counter = shoppingCartService.getCountProductsInCategory(category);
		} else
			counter = shoppingCartService.getCountProducts();

		List<Product> products = shoppingCartService.getEnabledProducts(selectedCategory, page, size);
		List<Category> categories = (List<Category>) shoppingCartService.getCategories(page, size);

		modelAndView.addObject("products", products);
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("category", selectedCategory);
		modelAndView.addObject("cart", session.getAttribute("cart"));
		modelAndView.addObject("counter", counter);

		modelAndView.setViewName("products");
		// Testing
		// System.out.println("*************** " + counter);

		return modelAndView;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer categoryId, HttpSession session) {
		initializeSession(session);
		Product product = shoppingCartService.getProductById(id);

		// Testing
		// System.out.println("******"+product.getName()+" u kategoriji
		// "+product.getCategory().getName());

		modelAndView.setViewName("product");
		modelAndView.addObject("product", product);
		modelAndView.addObject("categoryId", categoryId);

		return modelAndView;
	}

	@RequestMapping(value = "product/add/{id}/{quantity}", method = RequestMethod.GET)
	public ModelAndView addProductToCart(@PathVariable(value = "id") int id,
			@PathVariable(value = "quantity") int quantity, @RequestParam(required = false) Integer categoryId,
			HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		List<ShoppingCartItem> items = (List<ShoppingCartItem>) cart.getItems();

		// Testing
		// System.out.println("BEFORE ADDING "+cart.getTotalCost());

		if (cart.findItemByProductId(id) == null) {
			Product product = shoppingCartService.getProductById(id);
			ShoppingCartItem item = new ShoppingCartItem();

			item.setProduct(product);
			item.setQuantity(quantity);
			item.total = quantity * product.getPrice();
			item.setTotal(item.getQuantity() * product.getPrice());
			item.setShoppingCart(cart);

			items.add(item);
		} else {
			// edit existing ShoppingCartItem quantity
			cart.setTotalCost(cart.getTotalCost() - cart.findItemByProductId(id).getTotal());
			cart.findItemByProductId(id).setQuantity(cart.findItemByProductId(id).getQuantity() + quantity);
			cart.findItemByProductId(id).setTotal(
					cart.findItemByProductId(id).getQuantity() * cart.findItemByProductId(id).getProduct().getPrice());
			items.set(items.indexOf(cart.findItemByProductId(id)), cart.findItemByProductId(id));
		}

		cart.setTotalCost(cart.getTotalCost() + cart.findItemByProductId(id).getTotal());
		cart.setItems(items);
		session.setAttribute("cart", cart);

		// Testing
		// System.out.println("AFTER ADDING "+cart.getTotalCost());

		if (categoryId == null || categoryId == 0)
			return new ModelAndView("redirect:/products");
		else
			return new ModelAndView("redirect:/products?category=" + categoryId);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		modelAndView.setViewName("cart");
		modelAndView.addObject("cart", session.getAttribute("cart"));
		return modelAndView;
	}

	@RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable(value = "id") int id, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();

		// Testing
		// System.out.println("BEFORE REMOVING " + cart.getTotalCost());

		Iterator<ShoppingCartItem> itr = items.iterator();
		while (itr.hasNext()) {
			ShoppingCartItem element = (ShoppingCartItem) itr.next();
			if (element.getProduct().getId() == id) {
				cart.setTotalCost(cart.getTotalCost() - element.getTotal());
				itr.remove();
			}
		}

		session.setAttribute("cart", cart);
		// Testing
		// System.out.println("AFTER REMOVING" + cart.getTotalCost());
		return new ModelAndView("redirect:/cart");

	}

	@RequestMapping(value = { "/cart/deleteAll" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpSession session) {
		initializeSession(session);
		session.setAttribute("cart", new ShoppingCart());
		return new ModelAndView("redirect:/products");
	}

	@RequestMapping(value = "/cart/edit/{id}", method = RequestMethod.GET)
	public ModelAndView changeQuantityOfItem(@PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer value, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		// Testing
		// System.out.println("BEFORE EDIT QUANTITY " + cart.getTotalCost());

		if (cart.findItemByProductId(id) != null) {
			cart.findItemByProductId(id).setQuantity(value);
			Double currentTotal = cart.getTotalCost() - cart.findItemByProductId(id).getTotal();
			cart.findItemByProductId(id).setTotal(
					cart.findItemByProductId(id).getQuantity() * cart.findItemByProductId(id).getProduct().getPrice());

			cart.setTotalCost(currentTotal + cart.findItemByProductId(id).getTotal());
		}

		// Testing
		// System.out.println("AFTER EDIT QUANTITY " + cart.getTotalCost());

		return new ModelAndView("redirect:/cart");
	}

	@RequestMapping(value = "/user/payStep1", method = RequestMethod.GET)
	public ModelAndView payStep1(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		// odabir nacina placanja
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.setCustomer(customerService.getCustomerById(getAuthenticatedUser().getId()));
		modelAndView.setViewName("confirmPurchase");
		return modelAndView;
	}

	@RequestMapping(value = "/user/confirmPurchase", method = RequestMethod.GET)
	public ModelAndView confirmPurchase(ModelAndView modelAndView, @RequestParam(required = true) String paymentMethod,
			HttpSession session) {
		initializeSession(session);

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//		cart.setCustomer(customerService.getCustomerById(getAuthenticatedUser().getId()));
		cart.setPaymentMethod(paymentMethod);
		cart.setStatus("pending");
		cart.setEnabled(true);
		shoppingCartService.saveCart(cart);
		session.setAttribute("cart", new ShoppingCart());
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("redirect:/products");
		return modelAndView;
	}
}
