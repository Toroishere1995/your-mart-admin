package com.learning.yourmartpmp.dto;

/**
 * DTO class for user details.
 * @author ayushsaxena
 *
 */
public class UserDto {
	private String token;
	private Object userInfo;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
}
