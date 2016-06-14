package com.ShoppingCart.dao;

import java.util.ArrayList;

import com.ShoppingCart.entity.Customer;

public interface CustomerDao {

	public ArrayList<Customer> getAllCustomers();

	public Customer getCustomer(int id);
	
	public void addCustomer(Customer customer);
	
	public void editCustomer(int id, Customer newCustomer);
	
	public void deleteCustomer(int id);
	

}
