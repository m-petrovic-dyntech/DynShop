package com.ShoppingCart.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ShoppingCart.dto.UserDto;
import com.ShoppingCart.entity.City;
import com.ShoppingCart.entity.Municipality;
import com.ShoppingCart.service.CustomerService;
import com.ShoppingCart.util.ControllerUtil;
import com.ShoppingCart.util.GenericResponse;

@Controller
// @PropertySource("classpath:test.properties")
@PropertySource({ "classpath:/application.properties", "classpath:/test.properties" })
public class RegistrationController extends ControllerUtil {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ConversionService conversionService;

	@Resource
	private Environment environment;

	@Resource
	private Environment testUtil;

	/*
	 * @Autowired private Environment env;
	 */

	@RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public GenericResponse registerUserAccount(@Valid @RequestBody UserDto userDto, BindingResult bindingResult,
			HttpServletRequest request) {
		logger.info("Registering user account with information: {}" + userDto.toString());

		System.out.println("TEST " + environment.getProperty("database.url"));
		System.out.println("TEST " + testUtil.getProperty("test"));
		/* System.out.println("APP " + name); */
		try {
			if (bindingResult.hasErrors()) {
				return new GenericResponse("Validation error", bindingResult.getFieldError().toString());
			}
			// final UserAccount userAccount =
			// userService.registerNewUserAccount(userDto);
			return new GenericResponse("Registration succesfull");
		} catch (Exception e) {
			return new GenericResponse("User or mail already exists!", "Already taken!");
		}
		// final Customer registered =
		// customerService.registerNewUserAccount(userDto);
		// eventPublisher.publishEvent(new
		// OnRegistrationCompleteEvent(registered, request.getLocale(),
		// getAppUrl(request)));

	}

	@RequestMapping(value = { "/registration/municipality" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Municipality>> getMunicipalityById(ModelAndView modelAndView,
			@RequestParam("id") int id) {
		City city = conversionService.convert(id, City.class);
		System.out.println("*****************   " + city.toString());
		List<Municipality> municipalityList = customerService.getMunicipalityByCity(city);
		System.out.println("*************  " + municipalityList);

		return new ResponseEntity<List<Municipality>>(municipalityList, HttpStatus.OK);
	}
}
