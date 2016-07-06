package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.dto.UserDetailsDto;
import com.ShoppingCart.entity.City;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Municipality;
import com.ShoppingCart.entity.Role;
import com.ShoppingCart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers(Integer page, Integer size) {
		if (page == null || size == null)
			return customerDao.getAllCustomers();
		else
			return customerDao.getAllCustomers(page, size);
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDao.getCustomerById(id);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	@Override
	public void editCustomer(Customer newCustomer) {
		customerDao.editCustomer(newCustomer);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer c = (Customer) this.getCustomerByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role r : c.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		return new UserDetailsDto(c.getUsername(), c.getPassword(), true, true, true, true, authorities, c.getId());
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return (Customer) customerDao.getCustomerByUsername(username);
	}

	@Override
	public List<Customer> getEnabledCustomers() {
		return customerDao.getEnabledCustomers();
	}

	@Override
	public List<Customer> getDisabledCustomers() {
		return customerDao.getDisabledCustomers();
	}

	@Override
	public int getCountCustomer() {
		return customerDao.getCountCustomer();
	}

	@Override
	public List<Role> getRolesByCustomer(Customer customer) {
		return customerDao.getRolesByCustomer(customer);
	}

	@Override
	public void addRole(Role role) {
		customerDao.addRole(role);	
	}

	@Override
	public void editRole(Role role) {
		customerDao.editRole(role);
		
	}

	@Override
	public void addRoleToCustomer(Role role, Customer customer) {
		customerDao.addRoleToCustomer(role, customer);
	}

	@Override
	public void removeRole(Role role) {
		customerDao.removeRole(role);
	}

	@Override
	public Role getRoleById(int id) {
		return customerDao.getRoleById(id);
	}

	
	public Role getRoleByTitle(String title) {
		return customerDao.getRoleByTitle(title);
	}

	@Override
	public List<Role> getRoles() {
		return customerDao.getRoles();
	}

	@Override
	public List<City> getAllCities() {
		return customerDao.getAllCities();
	}

	@Override
	public City getCityById(int id) {
		return customerDao.getCityById(id);
	}

	@Override
	public List<Municipality> getMunicipalityByCity(City city) {
		return customerDao.getMunicipalityByCity(city);
	}

}
