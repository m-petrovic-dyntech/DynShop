package com.ShoppingCart.dao.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.entity.Customer;

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
		getSession().getTransaction().commit();
	}

	@Override
	@Transactional
	public void editCustomer(int id, Customer newCustomer) {
		newCustomer.setId(id);
		getSession().saveOrUpdate(newCustomer);
		getSession().getTransaction().commit();
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		getSession().delete(id);
		getSession().getTransaction().commit();
	}

}
