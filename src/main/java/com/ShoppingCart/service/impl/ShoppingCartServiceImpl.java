package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;
import com.ShoppingCart.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public List<Product> getProducts(Category category) {
		if(category==null)
			return shoppingCartDao.getProducts();
		else
			return shoppingCartDao.getProductsByCategory(category);
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
	public Category getCategoryById(Integer id) {
		if (id == null)
			return new Category();
		return shoppingCartDao.getCategoryById(id);
	}

	@Override
	public void saveCart(ShoppingCart cart) {
		shoppingCartDao.saveCart(cart);
	}
	
	@Override
	public List<Product> getProductsByCategory(Category category) {
		return shoppingCartDao.getProductsByCategory(category);
	}
	
	@Override
	public List<ShoppingCart> getCartsByCustomer(Customer customer) {
		return shoppingCartDao.getCartsByCustomer(customer);
	}

	@Override
	public List<ShoppingCart> getAllCarts() {
		return shoppingCartDao.getAllCarts();
	}

	@Override
	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart) {
		return shoppingCartDao.getItemsByCart(cart);
	}

	@Override
	public void editCategory(Category category) {
		shoppingCartDao.editCategory(category);
		
	}

	@Override
	public void addCategory(Category category) {
		shoppingCartDao.addCategory(category);
		
	}

	@Override
	public void deactivateCategory(Category category) {
		shoppingCartDao.deactivateCategory(category);
		
	}

	@Override
	public void editProduct(Product product) {
		shoppingCartDao.editProduct(product);
		
	}

	@Override
	public void deactivateProduct(Product product) {
		shoppingCartDao.deactivateProduct(product);;
		
	}

	@Override
	public void addProduct(Product product) {
		shoppingCartDao.addProduct(product);
		
	}
}
