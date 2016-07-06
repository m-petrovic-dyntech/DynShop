package com.ShoppingCart.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="city")
public class City {
	@Id
	@SequenceGenerator(name="city_gen", sequenceName="city_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@OneToMany(mappedBy = "city")
	@JsonIgnore
	private List<Municipality> municipalities;
	
	@OneToMany(mappedBy="city")
	@JsonIgnore
	private List<UserAccount> users;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}
	
	

	public List<UserAccount> getUsers() {
		return users;
	}

	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "City name=" + name + ", zipCode=" + zipCode ;
	}
	
	
}
