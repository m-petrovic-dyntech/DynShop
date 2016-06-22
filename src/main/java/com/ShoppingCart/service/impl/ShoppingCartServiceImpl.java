package com.ShoppingCart.service.impl;

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
	public List<Product> getProducts(Category category, Integer page, Integer size) {
		if (category == null || category.getName() == null)
			if (page == null || size == null)
				return shoppingCartDao.getProducts();
			else
				return shoppingCartDao.getProducts(page, size);
		else if (page == null || size == null)
			return shoppingCartDao.getProductsByCategory(category);
		else
			return shoppingCartDao.getProductsByCategory(category, page, size);
	}

	@Override
	public Product getProductById(int id) {
		return shoppingCartDao.getProductById(id);
	}

	@Override
	public List<Category> getCategories(Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getCategories();
		else
			return shoppingCartDao.getCategories(page, size);
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
	public List<ShoppingCart> getCartsByCustomer(Customer customer, Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getCartsByCustomer(customer);
		else
			return shoppingCartDao.getCartsByCustomer(customer, page, size);
	}

	@Override
	public List<ShoppingCart> getAllCarts(Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getAllCarts();
		else
			return shoppingCartDao.getAllCarts(page, size);
	}

	@Override
	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart, Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getItemsByCart(cart);
		else
			return shoppingCartDao.getItemsByCart(cart, page, size);
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
	public void editProduct(Product product) {
		shoppingCartDao.editProduct(product);

	}

	@Override
	public void addProduct(Product product) {
		shoppingCartDao.addProduct(product);

	}

	@Override
	public List<Category> getEnabledCategories(Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getEnabledCategories();
		else
			return shoppingCartDao.getEnabledCategories(page, size);
	}

	@Override
	public List<Category> getDisabledCategories(Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getDisabledCategories();
		else
			return shoppingCartDao.getDisabledCategories(page, size);
	}

	@Override
	public List<Product> getEnabledProducts(Category category, Integer page, Integer size) {
		if (category == null || category.getName() == null)
			if (page == null || size == null)
				return shoppingCartDao.getEnabledProducts();
			else
				return shoppingCartDao.getEnabledProducts(page, size);
		else if (page == null || size == null)
			return shoppingCartDao.getEnabledProductsByCategory(category);
		else
			return shoppingCartDao.getEnabledProductsByCategory(category, page, size);
	}

	@Override
	public List<Product> getDisabledProducts(Integer page, Integer size) {
		if (page == null || size == null)
			return shoppingCartDao.getDisabledProducts();
		else
			return shoppingCartDao.getDisabledProducts(page, size);
	}
	
	@Override
	public int getCountCategories() {
		return shoppingCartDao.getCountCategories();
	}

	@Override
	public int getCountProducts() {
		return shoppingCartDao.getCountProducts();
	}

	@Override
	public int getCountProductsInCategory(int categoryId) {
		return shoppingCartDao.getCountProductsInCategory(categoryId);
	}

	@Override
	public int getCountCarts() {
		return shoppingCartDao.getCountCarts();
	}

	@Override
	public int getCountCartsInCustomer(Customer customer) {
		return shoppingCartDao.getCountCartsInCustomer(customer);
	}

	@Override
	public int getCountItemsInCart(int id) {
		return shoppingCartDao.getCountItemsInCart(id);
	}

	@Override
	public List<Double> getAllCartsPurcashedByMonth(int month) {
		return shoppingCartDao.getAllCartsPurcashedByMonth(month);
	}

	@Override
	public List<ShoppingCart> getPendingCarts() {
		return shoppingCartDao.getPendingCarts();
	}

	@Override
	public ShoppingCart getCartById(Integer id) {
		return shoppingCartDao.getCartById(id);
	}

	@Override
	public void editCart(ShoppingCart cart) {
		shoppingCartDao.editCart(cart);	
	}

	
}
