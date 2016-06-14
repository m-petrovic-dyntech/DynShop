package com.ShoppingCart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;

public class CustomerDaoImpl implements CustomerDao {

	
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	
	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Session session;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
	  return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) getSession().createCriteria(Customer.class).list();
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return (Customer) getSession().createCriteria(Customer.class).add(Restrictions.eq("id",id)).uniqueResult();
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		getSession().save(customer);
	}

	@Override
	@Transactional
	public void editCustomer(Customer newCustomer) {
		getSession().saveOrUpdate(newCustomer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		getSession().delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ShoppingCart> getCartsByCustomerId(int id) {
		Customer customer= getCustomer(id);
		return (ArrayList<ShoppingCart>) getSession().createCriteria(ShoppingCart.class).add(Restrictions.eq("customer", customer)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ShoppingCart> getAllCarts() {
		return  (ArrayList<ShoppingCart>) getSession().createCriteria(ShoppingCart.class).list();
	}

}
