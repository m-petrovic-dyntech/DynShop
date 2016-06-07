package com.ShoppingCart.dao.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.EntityTest;
import com.ShoppingCart.entity.Product;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private final Log logger = LogFactory.getLog(getClass());
		
	private SessionFactory sessionFactory;
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	  this.sessionFactory = sessionFactory;
	}
	 
	public Session getSession() {
	  return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<Product> getProducts() {
		ArrayList<Product> results = (ArrayList<Product>)getSession().createCriteria(Product.class).list();	
		return results;
	}

	@Transactional
	@Override
	public Product getProduct(int id) {
		Product product =  (Product)getSession().createCriteria(Product.class).add(Restrictions.eq("id",id)).uniqueResult();
		return product;
		
	}

}
