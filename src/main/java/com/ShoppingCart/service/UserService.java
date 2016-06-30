package com.ShoppingCart.service;

import com.ShoppingCart.dto.UserDto;
import com.ShoppingCart.entity.UserAccount;

public interface UserService{
	public UserAccount registerNewUserAccount(UserDto userDto) throws Exception;

}
