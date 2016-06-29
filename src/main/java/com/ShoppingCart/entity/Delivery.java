package com.ShoppingCart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="delivery")
public class Delivery {

	@Id
	@SequenceGenerator(name="delivery_gen", sequenceName="delivery_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="delivery_gen")
	@Column(name="id")
	private Integer id;
	
	@OneToMany(mappedBy="delivery")
	private List<ShoppingCart> carts;  
	
	@Column(name="status")
	private String status;
	
	@Column(name="note")
	private String note;
	
	
	public Delivery()
	{
		this.carts = new ArrayList<ShoppingCart>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ShoppingCart> getCarts() {
		return carts;
	}

	public void setCarts(List<ShoppingCart> carts) {
		this.carts = carts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
