package com.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ShoppingCart.dao.RoleDao;
import com.ShoppingCart.dao.UserDao;
import com.ShoppingCart.dto.UserDetailsDto;
import com.ShoppingCart.dto.UserDto;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Role;
import com.ShoppingCart.entity.UserAccount;
import com.ShoppingCart.service.UserService;

public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccount account = this.getUserAccByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role r : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		return new UserDetailsDto(account.getUsername(), account.getPassword(), true, true, true, true, authorities,
				account.getId());

	}

	private UserAccount getUserAccByUsername(String username) {

		return userDao.getUserAccByUsername(username);
	}

	
	@Override
	public UserAccount registerNewUserAccount(UserDto userDto) throws Exception {

		if(userExist(userDto.getUsername(), userDto.getEmail())){
			throw new Exception();
		}
		
		UserAccount userAcc = new UserAccount();
		userAcc.setUsername(userDto.getUsername());
		userAcc.setPassword(userDto.getPassword());
		userAcc.setAddress(userDto.getAddress());
		//userAcc.setCity(userDto.getCity());
		userAcc.setFirstName(userDto.getFirstName());
		userAcc.setLastName(userDto.getLastName());
		userAcc.setEmail(userDto.getEmail());
		userAcc.setPhone(userDto.getPhone());
		
		
		Role role = roleDao.getRoleByName("ROLE_USER");		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		userAcc.setRoles(roles);
		userDao.saveUser(userAcc);

		return userAcc;
	}

	private boolean userExist(String username, String email) {
		final UserAccount userAcc = userDao.findByEmailAndPassword(username, email);
        if (userAcc != null) {
            return true;
        }
        return false;
	}

}
