package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.City;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Municipality;
import com.ShoppingCart.entity.Role;

public interface CustomerDao {

	public List<Customer> getAllCustomers();

	public List<Customer> getAllCustomers(int pageNum, int pageSize);

	public Customer getCustomerById(int id);

	//da li treba da postoji add?
	public void addCustomer(Customer customer);

	//edit profile?
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
	
	public Role getRoleByTitle(String title);
	
	public List<Role> getRoles();
	
	public List<City> getAllCities();
	
	public City getCityById(int id);
	
	public List<Municipality> getMunicipalityByCity(City city);
}
