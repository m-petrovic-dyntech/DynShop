package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.dto.CustomUserDetails;
import com.ShoppingCart.dto.UserDto;
import com.ShoppingCart.entity.Customer;
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
		return new CustomUserDetails(c.getUsername(), c.getPassword(), true, true, true, true, authorities, c.getId());
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

	@Override
	public Customer registerNewUserAccount(UserDto userDto) throws Exception{
		
		if(customerDao.emailExist(userDto.getEmail()))
			throw new Exception();
		
		Customer user = new Customer();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setAddress(userDto.getAddress());
		user.setCity(userDto.getCity());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		
		List<Role> roleList = new ArrayList<>();
		
		/*
		Role role = new Role();
		role.setId();
		
		roleList.se
		user.setRoles(roles);
		*/
		
		return null;
	}

}
