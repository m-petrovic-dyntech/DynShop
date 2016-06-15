package com.ShoppingCart.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ShoppingCart.dto.CustomUserDetails;
import com.ShoppingCart.entity.ShoppingCart;

public class ControllerUtil {
	public void initializeSession(HttpSession session) {
		if (session.getAttribute("cart") == null)
			session.setAttribute("cart", new ShoppingCart());
	}

	public Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}

	public CustomUserDetails getAuthenticatedUser() {
		CustomUserDetails cud = (CustomUserDetails) getAuthentication().getPrincipal();
		return cud;
	}
}
