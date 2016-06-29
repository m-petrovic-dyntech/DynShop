package com.ShoppingCart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ShoppingCart.dto.UserDto;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.service.MailService;
import com.ShoppingCart.util.ControllerUtil;
import com.ShoppingCart.util.GenericResponse;

@Controller
public class RegistrationController extends ControllerUtil{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private CustomerService customerService;
	
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid final UserDto userDto, final HttpServletRequest request) throws Exception {
    	logger.debug("Registering user account with information: {}" + userDto.toString() );

        final Customer registered = customerService.registerNewUserAccount(userDto);
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
	
	

}
