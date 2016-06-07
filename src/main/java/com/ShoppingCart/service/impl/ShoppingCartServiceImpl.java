package com.ShoppingCart.service.impl;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	/*
	 public void setShoppingCartDao(ShoppingCartDao movieDao) {
	  this.shoppingCartDao = movieDao;
	 } */

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public void testHibernate() {
		shoppingCartDao.testWithHibernate();
	}

	@Override
	public ArrayList<Product> getProducts() {
		
		return shoppingCartDao.getProducts();
	}

	@Override
	public Product getProduct(int id) {
		return shoppingCartDao.getProduct(id);
	}
}
