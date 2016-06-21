package com.ShoppingCart.service;

import java.util.List;

import com.ShoppingCart.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers(Integer page, Integer size);
	
	public int getCustomerCount();

	public Customer getCustomerById(int id);

	public void addCustomer(Customer customer);

	public void editCustomer(Customer newCustomer);

	public Customer getCustomerByUsername(String username);

	public List<Customer> getEnabledCustomers();

	public List<Customer> getDisabledCustomers();

}
