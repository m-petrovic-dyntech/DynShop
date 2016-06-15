package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.dto.CustomUserDetails;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Role;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDao.getCustomerById(id);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	@Override
	public void editCustomer(Customer newCustomer) {
		customerDao.editCustomer(newCustomer);
	}

	@Override
	public void deactivateCustomer(int id) {
		customerDao.deactivateCustomer(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer c = (Customer) this.getCustomerByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role r : c.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		return new CustomUserDetails(c.getUsername(), c.getPassword(), true, true, true, true, authorities, c.getId());
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return (Customer) customerDao.getCustomerByUsername(username);
	}

	@Override
	public List<Customer> getActiveCustomers() {
		return customerDao.getActiveCustomers();
	}

	@Override
	public List<Customer> getInactiveCustomers() {
		return customerDao.getInactiveCustomers();
	}
}
