package com.ShoppingCart.dao;

import java.util.ArrayList;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;

public interface CustomerDao {

	public ArrayList<Customer> getAllCustomers();

	public Customer getCustomerById(int id);
	
	public void addCustomer(Customer customer);
	
	public void editCustomer(Customer newCustomer);
	
	public void deleteCustomer(int id);
	
	public ArrayList<ShoppingCart> getCartsByCustomerId(int id);
	
	public ArrayList<ShoppingCart> getAllCarts();

}
