package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.Customer;

public interface CustomerDao {

	public List<Customer> getAllCustomers();
	
	public List<Customer> getAllCustomers(int pageNum, int pageSize);
	
	public int getCustomerCount();

	public Customer getCustomerById(int id);
	
	public void addCustomer(Customer customer);

	public void editCustomer(Customer newCustomer);

	public Customer getCustomerByUsername(String username);

	public List<Customer> getEnabledCustomers();
	
	public List<Customer> getDisabledCustomers();
}
