package com.learning.yourmartpmp.dto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DTO class for product details.
 * @author ayushsaxena
 *
 */
public class ProductDetails {


	private int productId;

	private String sellerProductCode;

	private String productName;

	private String productShortDescription;

	private String productLongDescription;

	private String productDimensions;

	private String productCategory;

	private double productMrp;

	private double productSsp;

	private double productYmp;

	private String productPrimaryImage;

	private String productStatus;

	private String productComment;

	private Collection<ProductImagesCredentialsDto> productImages = new ArrayList<>();

	public String getProductCategory() {
		return productCategory;
	}

	public String getProductComment() {
		return productComment;
	}

	public String getProductDimensions() {
		return productDimensions;
	}

	public int getProductId() {
		return productId;
	}

	public Collection<ProductImagesCredentialsDto> getProductImages() {
		return productImages;
	}

	public String getProductLongDescription() {
		return productLongDescription;
	}

	public double getProductMrp() {
		return productMrp;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductPrimaryImage() {
		return productPrimaryImage;
	}

	public String getProductShortDescription() {
		return productShortDescription;
	}

	public double getProductSsp() {
		return productSsp;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public double getProductYmp() {
		return productYmp;
	}

	public String getSellerProductCode() {
		return sellerProductCode;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	public void setProductDimensions(String productDimensions) {
		this.productDimensions = productDimensions;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductImages(Collection<ProductImagesCredentialsDto> productImages) {
		this.productImages = productImages;
	}

	public void setProductLongDescription(String productLongDescription) {
		this.productLongDescription = productLongDescription;
	}

	public void setProductMrp(double productMrp) {
		this.productMrp = productMrp;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrimaryImage(String productPrimaryImage) {
		this.productPrimaryImage = productPrimaryImage;
	}

	public void setProductShortDescription(String productShortDescription) {
		this.productShortDescription = productShortDescription;
	}

	public void setProductSsp(double productSsp) {
		this.productSsp = productSsp;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public void setProductYmp(double productYmp) {
		this.productYmp = productYmp;
	}

	public void setSellerProductCode(String sellerProductCode) {
		this.sellerProductCode = sellerProductCode;
	}

}
