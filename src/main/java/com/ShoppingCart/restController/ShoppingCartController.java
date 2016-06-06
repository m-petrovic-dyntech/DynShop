package com.ShoppingCart.restController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.service.ShoppingCartService;


@RestController
@RequestMapping(value = "/rest")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;

	private final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<String> test() {
	
		System.out.println("*******");
		shoppingCartService.testHibernate();
		return new ResponseEntity<String>("SAVED!", HttpStatus.OK);
	}
	
}
