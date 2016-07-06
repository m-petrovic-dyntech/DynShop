package com.ShoppingCart.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	@NotNull
	@Size(min = 1)
	private String username;

	private String password;
	
	@NotNull
	@Size(min = 1)
	private String matchingPassword;

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	@NotNull
	@Size(min = 1)
	private String firstName;

	@NotNull
	@Size(min = 1)
	private String lastName;

	@NotNull
	@Size(min = 6)
	private String phone;

	@NotNull
	@Size(min = 1)
	private String address;

	@NotNull
	@Size(min = 1)
	private String city;
	
	@NotNull
	@Size(min = 1)
	private String municipality;
	
	@NotNull
	@Size(min = 1)
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", matchingPassword=" + matchingPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", address=" + address
				+ ", city=" + city + ", municipality=" + municipality + ", email=" + email + "]";
	}

	
}
