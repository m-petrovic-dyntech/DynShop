package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public ArrayList<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
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
	public void deleteCustomer(int id) {
		customerDao.deleteCustomer(id);
	}

}
