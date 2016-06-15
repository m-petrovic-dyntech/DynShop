package com.ShoppingCart.service;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;

public interface ShoppingCartService {
	
	public List<Product> getProducts(Category category);

	public Product getProduct(int id);
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(Integer id);
	
	public void saveCart(ShoppingCart cart);

	public List<Product> getProductsByCategory(Category category);

	public List<ShoppingCart> getCartsByCustomerId(int id);

	public List<ShoppingCart> getAllCarts();
}
