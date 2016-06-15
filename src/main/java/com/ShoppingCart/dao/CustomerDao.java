package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.Customer;

public interface CustomerDao {

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(int id);
	
	public void addCustomer(Customer customer);

	public void editCustomer(Customer newCustomer);

	public void deactivateCustomer(int id);

	public Customer getCustomerByUsername(String username);

	public List<Customer> getActiveCustomers();
	
	public List<Customer> getInactiveCustomers();
}
