package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ShoppingCart.dto.JtoPagination;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.MailService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.util.CartStatus;
import com.ShoppingCart.util.ControllerUtil;


@Controller
public class AnonymousController extends ControllerUtil {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MailService mailService;

	// redirection based on role
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);

		if (ifUserHasRole("ROLE_ADMIN"))
			modelAndView.setViewName("redirect:/admin/panel/products");
		else if (ifUserHasRole("ROLE_STORAGE"))
			modelAndView.setViewName("redirect:/storage_management/pendingOrders");
		else if (ifUserHasRole("ROLE_USER"))
			modelAndView.setViewName("redirect:/products");

		modelAndView.addObject("customer", new Customer());
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userLogin(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);

		modelAndView.addObject("customer", new Customer());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView accesssDenied(ModelAndView modelAndView, HttpSession session) {

		modelAndView.addObject("user", getAuthenticatedUser().getUsername());
		modelAndView.setViewName("denied");
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
			HttpSession session, @ModelAttribute("infoQuantity") final String infoQuantity) {
		initializeSession(session);

		Category selectedCategory = new Category();
		JtoPagination pagination;

		if (category != null && category != 0) {
			selectedCategory = shoppingCartService.getCategoryById(category);
			pagination = new JtoPagination(page, size, shoppingCartService.getCountProductsInCategory(category));

		} else
			pagination = new JtoPagination(page, size, shoppingCartService.getCountProducts());

		List<Product> products = shoppingCartService.getEnabledProducts(selectedCategory, page, size);
		List<Category> categories = (List<Category>) shoppingCartService.getCategories(null, null);

		modelAndView.addObject("products", products);
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("category", selectedCategory);
		modelAndView.addObject("cart", session.getAttribute("cart"));
		modelAndView.addObject("pagination", pagination);
		modelAndView.addObject("infoQuantity", infoQuantity);
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer categoryId, HttpSession session) {
		initializeSession(session);
		Product product = shoppingCartService.getProductById(id);

		modelAndView.setViewName("product");
		modelAndView.addObject("product", product);
		modelAndView.addObject("categoryId", categoryId);

		return modelAndView;
	}

	@RequestMapping(value = "product/add/{id}", method = RequestMethod.GET)
	public ModelAndView addProductToCart(@PathVariable(value = "id") int id,
			@RequestParam(required = false) Integer category, @RequestParam(required = true) Integer quantity,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		List<ShoppingCartItem> items = (List<ShoppingCartItem>) cart.getItems();
		Product product = shoppingCartService.getProductById(id);
		String infoQuantity;

		if (quantity <= product.getQuantityInStock() - product.getReservedQuantity()) {

			if (cart.findItemByProductId(id) == null) {

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
				cart.findItemByProductId(id).setTotal(cart.findItemByProductId(id).getQuantity()
						* cart.findItemByProductId(id).getProduct().getPrice());
				items.set(items.indexOf(cart.findItemByProductId(id)), cart.findItemByProductId(id));
			}

			cart.setTotalCost(cart.getTotalCost() + cart.findItemByProductId(id).getTotal());
			cart.setItems(items);
			session.setAttribute("cart", cart);
			infoQuantity = "Quantity is ok";
		} else
			infoQuantity = "Quantity isn't ok";

		redirectAttributes.addFlashAttribute("infoQuantity", infoQuantity);
		return new ModelAndView(getRedirectLink("redirect:/products", request, Arrays.asList("quantity")));
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
		Iterator<ShoppingCartItem> itr = items.iterator();
		while (itr.hasNext()) {
			ShoppingCartItem element = (ShoppingCartItem) itr.next();
			if (element.getProduct().getId() == id) {
				cart.setTotalCost(cart.getTotalCost() - element.getTotal());
				itr.remove();
			}
		}
		session.setAttribute("cart", cart);
		return new ModelAndView("redirect:/cart");

	}

	@RequestMapping(value = { "/cart/deleteAll" }, method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		ArrayList<ShoppingCartItem> items = (ArrayList<ShoppingCartItem>) cart.getItems();
		for (ShoppingCartItem item : items) {
			Product product = shoppingCartService.getProductById(item.getProduct().getId());
			product.setReservedQuantity(item.getProduct().getReservedQuantity());
			shoppingCartService.editProduct(product);
		}
		session.setAttribute("cart", new ShoppingCart()); 
		return new ModelAndView("redirect:/products");
	}

	@RequestMapping(value = "/cart/edit/{id}", method = RequestMethod.GET)
	public ModelAndView changeQuantityOfItem(@PathVariable(value = "id") int id,
			@RequestParam(required = true) Integer value, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		if (cart.findItemByProductId(id) != null) {
			cart.findItemByProductId(id).setQuantity(value);
			Double currentTotal = cart.getTotalCost() - cart.findItemByProductId(id).getTotal();
			cart.findItemByProductId(id).setTotal(
					cart.findItemByProductId(id).getQuantity() * cart.findItemByProductId(id).getProduct().getPrice());

			cart.setTotalCost(currentTotal + cart.findItemByProductId(id).getTotal());
		}
		return new ModelAndView("redirect:/cart");
	}

	@RequestMapping(value = "/user/checkOut", method = RequestMethod.GET)
	public ModelAndView checkOut(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.setCustomer(customerService.getCustomerById(getAuthenticatedUser().getId()));
		List<ShoppingCartItem> items = (List<ShoppingCartItem>) cart.getItems();

		for (ShoppingCartItem shoppingCartItem : items) {
			Product product = shoppingCartService.getProductById(shoppingCartItem.getProduct().getId());
			product.setReservedQuantity(product.getReservedQuantity() + shoppingCartItem.getQuantity());
			shoppingCartService.editProduct(product);
		}
		modelAndView.setViewName("confirmPurchase");
		return modelAndView;
	}

	@RequestMapping(value = "/user/confirmPurchase", method = RequestMethod.GET)
	public ModelAndView confirmPurchase(ModelAndView modelAndView, @RequestParam(required = true) String paymentMethod,
			HttpSession session) throws MessagingException {
		initializeSession(session);

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.setPaymentMethod(paymentMethod);
		cart.setStatus(CartStatus.PENDING);
		cart.setEnabled(true);
		shoppingCartService.saveCart(cart);

		if (paymentMethod.equals("cash"))
			mailService.sendPaymentOrder(customerService.getCustomerById(getAuthenticatedUser().getId()),
					"info@dyntechshop.com", "j.dumeljic@dyntechdoo.com", "Your payment order from DynTechShop",
					"paymentOrderTemplate.vm");

		session.setAttribute("cart", new ShoppingCart());
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("redirect:/products");
		return modelAndView;
	}
}
