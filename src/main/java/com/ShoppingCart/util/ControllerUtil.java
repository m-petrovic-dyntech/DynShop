package com.ShoppingCart.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
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
	
	public boolean ifUserHasRole(String role)
	{
		Collection<GrantedAuthority> gerantedAutorities = getAuthenticatedUser().getAuthorities();
		for (GrantedAuthority grantedAuthority : gerantedAutorities) {
			if (grantedAuthority.getAuthority().equals(role))
				return true;
		}
		return false;
	}
	
	public PagedListHolder getPaginatedList(List results, int page, int pageSize)
	{
		PagedListHolder pagedListHolder = new PagedListHolder(results);
		//int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
}
