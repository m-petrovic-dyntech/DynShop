package com.ShoppingCart.util;

public enum CartStatus {

	PENDING,
	PENDING_DELIVERY,
	DENIED_DELIVERY,
	FINISHED,
	FAILED;
	
	public String toString() {
        return name().toLowerCase();
        
        
    }
}
