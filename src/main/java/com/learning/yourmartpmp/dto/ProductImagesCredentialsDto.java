package com.learning.yourmartpmp.dto;

/**
 * DTO class for product images details.
 * @author ayushsaxena
 *
 */
public class ProductImagesCredentialsDto {
	private int imageId;

	private String imageName;

	private String image;

	public String getImage() {
		return image;
	}

	public int getImageId() {
		return imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
