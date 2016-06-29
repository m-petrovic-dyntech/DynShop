package com.ShoppingCart.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

	public boolean ifUserHasRole(String role) {
		Collection<GrantedAuthority> gerantedAutorities = getAuthenticatedUser().getAuthorities();
		for (GrantedAuthority grantedAuthority : gerantedAutorities) {
			if (grantedAuthority.getAuthority().equals(role))
				return true;
		}
		return false;
	}

	public String getReditectLink(String link, HttpServletRequest request, List<String> ignored) {

		Map<String, String[]> parameterMap = request.getParameterMap();
		String redirectParameters = link;

		Integer i = 1;
		if (parameterMap.size() > 0) {

			for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
				if (!ignored.contains(parameter.getKey())) {
					if (i < parameterMap.size() && i != 1) {
						redirectParameters += "&";
					} else {
						redirectParameters += "?";
					}
					redirectParameters += parameter.getKey() + "=" + parameter.getValue()[0];

				}
				i++;
			}
			System.out.println("***********  " + redirectParameters);

			return redirectParameters;
		} else {

			System.out.println("***********  " + redirectParameters);
			return link;
		}
	}
}
