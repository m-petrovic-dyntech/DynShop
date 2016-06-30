package com.ShoppingCart.dao;

import com.ShoppingCart.entity.UserAccount;

public interface UserDao {

	UserAccount getUserAccByUsername(String username);

}
