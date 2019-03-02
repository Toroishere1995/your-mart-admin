package com.learning.yourmartpmp.dto;

/**
 * DTO class for category details.
 * 
 * @author ayushsaxena
 *
 */
public class CategoryDetails {
	private int categoryId;

	private String categoryName;

	private int categoryProductsCount;

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryProductsCount() {
		return categoryProductsCount;
	}

	public void setCategoryProductsCount(int categoryProductsCount) {
		this.categoryProductsCount = categoryProductsCount;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
