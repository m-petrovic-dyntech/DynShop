package com.ShoppingCart.service;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

public interface ShoppingCartService {
	
	public List<Product> getProducts(Category category);

	public Product getProduct(int id);
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(Integer id);
	
	public void saveCart(ShoppingCart cart);

	public List<Product> getProductsByCategory(Category category);

	public List<ShoppingCart> getCartsByCustomer(Customer customer);

	public List<ShoppingCart> getAllCarts();
	
	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart);
}
