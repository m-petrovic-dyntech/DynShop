package com.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import com.ShoppingCart.dto.JtoPagination;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Role;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.util.ControllerUtil;

@Controller
public class AdminUserController extends ControllerUtil {
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
			List<Role> roles = customerService.getRolesByCustomer(customer);
			customer.setRoles(customerService.getRolesByCustomer(customer));
		}
		
		List<Role> roles= customerService.getUniqueRoles();
		
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
			HttpServletRequest request, HttpSession session) {
		initializeSession(session);

		Customer customer = (Customer) customerService.getCustomerById(id);
		customer.setEnabled(Boolean.FALSE);

		customerService.editCustomer(customer);

		modelAndView.setViewName(
				getRedirectLink("redirect:/admin/panel/users", request, Arrays.asList()));
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/addCustomer" }, method = RequestMethod.GET)
	public ModelAndView adminAddCustomer(ModelAndView modelAndView, @ModelAttribute("customerAddModel") Customer customer,
			HttpServletRequest request, HttpSession session) {

		customerService.addCustomer(customer);
		modelAndView.setViewName(
				getRedirectLink("redirect:/admin/panel/users", request, Arrays.asList()));
		return modelAndView;
	}
	
	@RequestMapping(value = { "/admin/panel/editCustomer" }, method = RequestMethod.GET)
	public ModelAndView adminEditCustomer(ModelAndView modelAndView,
			@ModelAttribute("customerEditModel") Customer customer,
			HttpServletRequest request, HttpSession session) {
		
		Customer toChangecustomer = customerService.getCustomerById(customer.getId());
		toChangecustomer.setFirstName(customer.getFirstName());
		toChangecustomer.setLastName(customer.getLastName());
		toChangecustomer.setCity(customer.getCity());
		toChangecustomer.setAddress(customer.getAddress());
		toChangecustomer.setPhone(customer.getPhone());
		toChangecustomer.setPassword(customer.getPassword());
		
		toChangecustomer.setEnabled(customer.getEnabled());
		Map<String, String[]> parameterMap = (Map<String, String[]>)request.getParameterMap();
		List<String> roles = new ArrayList<String>();
		
		for(String s : parameterMap.get("customer_roles")) { //iscitavanje stare role
			roles.add(s);
		}
		
		List<Role> toUpdate= customerService.getRolesByCustomer(customer);
		List<Role> toSet= new ArrayList<>();
		
		for (String string : roles) {
			System.out.println(string);
			Role r= customerService.getRoleByName(string).get(0);
			r.setId(null);
			for (Role role : toUpdate) {
				if(role.getRoleTitle().equals(r.getRoleTitle()))
					r.setEnabled(role.getEnabled());
			}
			r.setCustomer(customer);
			if(!toSet.contains(r))
				toSet.add(r);
		}
		
		for (Role role : toUpdate) {
			customerService.removeRole(role);
		}
		toChangecustomer.setRoles(toSet);

		customerService.editCustomer(toChangecustomer);
		modelAndView.setViewName(
				getRedirectLink("redirect:/admin/panel/users", request, Arrays.asList("id", "firstName", "lastName", "city", "address", "phone", "password", "customer_roles", "enabled")));
		return modelAndView;
	}

}
