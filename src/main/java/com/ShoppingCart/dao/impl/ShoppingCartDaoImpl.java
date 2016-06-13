package com.ShoppingCart.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

@Repository

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private final Log logger = LogFactory.getLog(getClass());
	
	private SessionFactory sessionFactory;
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
	public ArrayList<Product> getProducts() {
		ArrayList<Product> results = (ArrayList<Product>) getSession().createCriteria(Product.class).list();	
		return results;
	}

	@Override
	@Transactional
	public Product getProduct(int id) {
		Product product = (Product) getSession().createCriteria(Product.class).add(Restrictions.eq("id",id)).uniqueResult();
		return product;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Category> getCategories() {
		ArrayList<Category> results = (ArrayList<Category>) getSession().createCriteria(Category.class).list();
		return results;
	}

	@Override
	@Transactional
	public Category getCategoryById(int id) {
		Category category = (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", id)).uniqueResult();
		return category;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProductsByCategory(Category category) {
		return (List<Product>)getSession().createCriteria(Product.class).add(Restrictions.eq("category", category)).list();
	}

	@Override
	@Transactional
	public void saveCart(ShoppingCart cart) {
		cart.setDate(new java.util.Date());
		getSession().saveOrUpdate(cart);
	}

}
