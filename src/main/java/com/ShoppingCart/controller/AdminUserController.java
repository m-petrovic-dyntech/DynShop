package com.ShoppingCart.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.Role;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class AdminUserController extends ControllerUtil{
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = { "/admin/panel/users" }, method = RequestMethod.GET)
	public ModelAndView adminGetUsers(ModelAndView modelAndView, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, HttpSession session) {

		initializeSession(session);
		JtoPagination pagination = new JtoPagination(page, size, customerService.getCountCustomer());
		List<Customer> customers = customerService.getAllCustomers(page, size);
		for (Customer customer : customers) {
			System.out.println("**************************************************");
			List<Role> roles = customerService.getRolesByCustomer(customer);
			for (Role role : roles) {
				System.out.println(customer.toString());
				System.out.println(role.toString());
			}
			System.out.println("*********************************************&&&");
			customer.setRoles(customerService.getRolesByCustomer(customer));

		}
		List<Role> roles= customerService.getUniqueRoles();
		for (Role role : roles) {
			System.out.println("rola"+ role);
		}
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("customers", customers);
		modelAndView.addObject("pagination", pagination);
		// System.out.println("*************** " +
		// pagination);
		modelAndView.setViewName("admin_panel_users");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/panel/deleteCustomer/{id}" }, method = RequestMethod.GET)
	public ModelAndView adminDeleteCustomer(ModelAndView modelAndView, @PathVariable(value = "id") int id,
			HttpSession session) {
		initializeSession(session);

		Customer customer = (Customer) customerService.getCustomerById(id);
		customer.setEnabled(Boolean.FALSE);

		customerService.editCustomer(customer);

		modelAndView.setViewName("redirect:/admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/addCustomer" }, method = RequestMethod.GET)
	public ModelAndView adminAddCustomer(ModelAndView modelAndView, @ModelAttribute("customerAddModel") Customer customer,
			HttpSession session) {

		customerService.addCustomer(customer);
		modelAndView.setViewName("redirect:/admin_panel_users");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/editCustomer" }, method = RequestMethod.GET)
	public ModelAndView adminEditCustomer(ModelAndView modelAndView, @ModelAttribute("customerEditModel") Customer customer,
			HttpSession session) {

		customerService.editCustomer(customer);
		modelAndView.setViewName("redirect:/admin_panel_users");
		return modelAndView;
	}

}
