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

import com.ShoppingCart.dto.JtoDelivery;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.ShoppingCartService;
import com.ShoppingCart.service.StorageManagementService;
import com.ShoppingCart.util.ControllerUtil;
import com.fasterxml.jackson.annotation.JsonFormat.Value;

@Controller
public class DeliveryController  extends ControllerUtil{

	
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
//	
//	@Autowired
//	
//	private ShoppingCartService shoppingCartService;
//
//	@Autowired
//	private CustomerService customerService;
	
	@Autowired
	private StorageManagementService storageManagementService;
	
	@RequestMapping(value = "/storage_management/pendingOrders", method = RequestMethod.GET)
	public ModelAndView getMail(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		
		
		List<Delivery> orders = storageManagementService.getPendingOrders();
		List<JtoDelivery> deliveries = new ArrayList<>();
		
		for (Delivery order : orders) {
			JtoDelivery delivery = new JtoDelivery();
			delivery.setCustomer(storageManagementService.getCustomerByDeliveryId(order.getId()));
			delivery.setCart(storageManagementService.getCartByDeliveryId(order.getId()));
			deliveries.add(delivery);
		} 

		modelAndView.addObject("orders", storageManagementService.getPendingOrders());
	    modelAndView.addObject("deliveries",deliveries);
		modelAndView.setViewName("storage_management_orders");
		return modelAndView;
	}
	
	//TODO REST poziv za get item iz korpe sa id_cart
	
	@RequestMapping(value = "/storage_management/sentOrder/{id}", method = RequestMethod.GET)
	public ModelAndView sentProduct(ModelAndView modelAndView, HttpSession session,@PathVariable(value = "id") int id,
			@RequestParam (required = true) String status) {
		initializeSession(session);
		
		Delivery delivery = storageManagementService.getOrderById(id);
		delivery.setStatus("sent");
		storageManagementService.changeDeliveryStatus(delivery);

		//sent mail to user
		//smanjujemo kolicnu prozvoda za rezervisano
		//reservisano umanjiti za broj prodatih
		modelAndView.setViewName("redirect:/storage_management/pendingOrders");
		return modelAndView;
	}
	
	@RequestMapping(value = "/storage_management/cancelOrder", method = RequestMethod.GET)
	public ModelAndView sentOrder(ModelAndView modelAndView, HttpSession session) {
		initializeSession(session);
		//TODO change status of delivery to "canceled"
		//sent mail to user
		//vratiti quantity prozvoda tj povecati 
		//reservisano umanjiti za broj cancelovanih
		modelAndView.setViewName("redirect:/storage_management/pendingOrders");
		return modelAndView;
	}
	
}
