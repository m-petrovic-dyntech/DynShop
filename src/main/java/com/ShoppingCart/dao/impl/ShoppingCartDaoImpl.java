package com.ShoppingCart.dao.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.util.SessionUtil;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private final Log logger = LogFactory.getLog(getClass());
	
	private SessionUtil sessionUtil;
	
	@Autowired
	public void setSessionUtil(SessionUtil sessionUtil) {
		this.sessionUtil = sessionUtil;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<Product> getProducts() {
		ArrayList<Product> results = (ArrayList<Product>) sessionUtil.getSession().createCriteria(Product.class).list();	
		return results;
	}

	@Transactional
	@Override
	public Product getProduct(int id) {
		Product product = (Product) sessionUtil.getSession().createCriteria(Product.class).add(Restrictions.eq("id",id)).uniqueResult();
		return product;
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<Category> getCategories() {
		ArrayList<Category> results = (ArrayList<Category>) sessionUtil.getSession().createCriteria(Category.class).list();
		return results;
	}

	@Transactional
	@Override
	public Category getCategoryById(int id) {
		Category category = (Category) sessionUtil.getSession().createCriteria(Category.class).add(Restrictions.eq("id", id)).uniqueResult();
		return category;
	}

}
