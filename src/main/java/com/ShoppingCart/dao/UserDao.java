package com.ShoppingCart.dao;

import com.ShoppingCart.entity.UserAccount;

public interface UserDao {

	UserAccount getUserAccByUsername(String username);

	UserAccount findByEmailAndPassword(String username, String email);
	
	public void saveUser(UserAccount userAccount);

}
