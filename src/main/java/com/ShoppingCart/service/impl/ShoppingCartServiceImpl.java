package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public ArrayList<Product> getProducts() {
		return shoppingCartDao.getProducts();
	}

	@Override
	public Product getProduct(int id) {
		return shoppingCartDao.getProduct(id);
	}

	@Override
	public ArrayList<Category> getCategories() {
		return shoppingCartDao.getCategories();
	}

	@Override
	public Category getCategoryById(int id) {
		return shoppingCartDao.getCategoryById(id);
	}
}
