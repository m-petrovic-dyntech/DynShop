package com.ShoppingCart.service;

import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

public interface ShoppingCartService {

	public Product getProductById(int id);

	public List<Product> getProducts(Category category, Integer page, Integer size);

	public List<Category> getCategories(Integer page, Integer size);
	
	public int getCategoriesCount();

	public Category getCategoryById(Integer id);

	public void saveCart(ShoppingCart cart);

	public List<ShoppingCart> getCartsByCustomer(Customer customer, Integer page, Integer size);

	public List<ShoppingCart> getAllCarts(Integer page, Integer size);

	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart, Integer page, Integer size);

	public void editCategory(Category category);

	public void addCategory(Category category);

	public void editProduct(Product product);

	public void addProduct(Product product);

	public List<Category> getEnabledCategories(Integer page, Integer size);

	public List<Category> getDisabledCategories(Integer page, Integer size);

	public List<Product> getEnabledProducts(Category category, Integer page, Integer size);

	public List<Product> getDisabledProducts(Integer page, Integer size);

}
