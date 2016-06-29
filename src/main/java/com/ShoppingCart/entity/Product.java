package com.ShoppingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="products", schema="DYNTECH_SHOP")
public class Product {
	@Id
	@SequenceGenerator(name="product_gen", sequenceName="product_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@Column(name="productType")
	private Integer productType;
	
	@Column(name="downloadLink")
	private String downloadLink;
	
	@Column(name="quantityInStock")
	private Integer quantityInStock;
	
	@Column(name="reservedQuantity")  //  nullable = false, columnDefinition = “int default 0"
	private Integer reservedQuantity; // = 0;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName="ID")
	private Category category;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Integer getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Integer reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}
	
}
