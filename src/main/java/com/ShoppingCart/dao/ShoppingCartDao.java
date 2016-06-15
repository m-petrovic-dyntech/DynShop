package com.ShoppingCart.dao;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

public interface ShoppingCartDao {

	public ArrayList<Product> getProducts();

	public Product getProduct(int id); 
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(int id);
	
	public void saveCart(ShoppingCart cart);
	
	public List<Product> getProductsByCategory(Category category);

	public List<ShoppingCart> getCartsByCustomerId(int id);
	
	public List<ShoppingCart> getAllCarts();

	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart);
 }
