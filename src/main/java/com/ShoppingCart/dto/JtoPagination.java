package com.ShoppingCart.dto;

public class JtoPagination {

	private Integer numberOfItems;
	private Integer pageSize;
	private Integer currentPage;

//	public JtoPagination() {
//		this.numberOfItems = numberOfItems;
//		this.pageSize = pageSize;
//		this.currentPage = currentPage;
//	}
//	
	public JtoPagination(Integer currentPage, Integer pageSize, Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
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
