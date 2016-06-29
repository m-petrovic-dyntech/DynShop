package com.ShoppingCart.service;

import java.util.List;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Role;

public interface CustomerService {

	public List<Customer> getAllCustomers(Integer page, Integer size);

	public Customer getCustomerById(int id);

	public void addCustomer(Customer customer);

	public void editCustomer(Customer newCustomer);

	public Customer getCustomerByUsername(String username);

	public List<Customer> getEnabledCustomers();

	public List<Customer> getDisabledCustomers();

	public int getCountCustomer();
	
	public List<Role> getRolesByCustomer(Customer customer);
		
	public void addRole(Role role);
	
	public void editRole(Role role);
	
	public void addRoleToCustomer(Role role, Customer customer);

	public void removeRole(Role role);
	
	public Role getRoleById(int id);
	
	public List<Role> getRoles();
	
	public Role getRoleByTitle(String title);
}
