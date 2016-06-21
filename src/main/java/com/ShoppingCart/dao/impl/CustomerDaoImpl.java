package com.ShoppingCart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.CustomerDao;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Role;


@Repository
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
	public int getCustomerCount() {
		Long result = (Long) getSession().createCriteria(Customer.class).setProjection(Projections.rowCount())
				.uniqueResult();
		return Integer.parseInt(result.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> getAllCustomers(int pageNum, int pageSize) {
		Criteria results = getSession().createCriteria(Customer.class);
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	
	@Override
	@Transactional
	public Customer getCustomerById(int id) {
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Customer getCustomerByUsername(String username) {
		Customer c = (Customer)getSession().createCriteria(Customer.class).add(Restrictions.eq("username", username)).uniqueResult();
		c.setRoles((List<Role>)getSession().createCriteria(Role.class).add(Restrictions.eq("customer", c)).list());
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getEnabledCustomers() {
		return (List<Customer>) getSession().createCriteria(Customer.class).add(Restrictions.eq("enabled", Boolean.TRUE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getDisabledCustomers() {
		return (List<Customer>) getSession().createCriteria(Customer.class).add(Restrictions.eq("enabled", Boolean.FALSE)).list();
	}

	

}
