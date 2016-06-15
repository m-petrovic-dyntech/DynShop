package com.ShoppingCart.dao;

import java.util.ArrayList;

import com.ShoppingCart.entity.Customer;

public interface CustomerDao {

	public ArrayList<Customer> getAllCustomers();

	public Customer getCustomerById(int id);
	
	public void addCustomer(Customer customer);
	
	public void editCustomer(Customer newCustomer);
	
	public void deleteCustomer(int id);

}
