package com.ShoppingCart.dto;

public class JtoPagination {

	private Integer numberOfItems;
	private Integer pageSize;
	private Integer currentPage;

	public JtoPagination() {
		this.numberOfItems = 0;
		this.pageSize = 0;
		this.currentPage = 0;
	}
	
	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "JtoPagination [currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", numberOfItems=" + numberOfItems + "]";
	}
	
}
