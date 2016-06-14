package com.ShoppingCart.service;

import java.util.ArrayList;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;

public interface CustomerService {
	public ArrayList<Customer> getAllCustomers();

	public Customer getCustomer(int id);
	
	public void addCustomer(Customer customer);
	
	public void editCustomer(Customer newCustomer);
	
	public void deleteCustomer(int id);
	
	public ArrayList<ShoppingCart> getCartsByCustomerId(int id);
	
	public ArrayList<ShoppingCart> getAllCarts();
}
