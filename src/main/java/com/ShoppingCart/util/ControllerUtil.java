package com.ShoppingCart.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ShoppingCart.entity.ShoppingCart;

public class ControllerUtil {
	public void initializeSession(HttpSession session) {
		if(session.getAttribute("cart") == null)
			session.setAttribute("cart", new ShoppingCart());
	}
	
	public Authentication getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
}
