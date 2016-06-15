package com.ShoppingCart.service;

import java.util.ArrayList;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;

public interface CustomerService {
	public ArrayList<Customer> getAllCustomers();

	public Customer getCustomerById(int id);

	public void addCustomer(Customer customer);

	public void editCustomer(Customer newCustomer);

	public void deleteCustomer(int id);

	public ArrayList<ShoppingCart> getCartsByCustomer(Customer customer);

	public ArrayList<ShoppingCart> getAllCarts();

	public Customer getCustomerByUsername(String username);

}
