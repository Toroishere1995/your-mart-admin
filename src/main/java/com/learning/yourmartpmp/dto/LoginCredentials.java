package com.learning.yourmartpmp.dto;

import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO class for login details.
 * 
 * @author ayushsaxena
 *
 */
@XmlRootElement
public class LoginCredentials {

	@NotEmpty(message = "Username Can't be empty")
	private String userName;

	@NotEmpty(message = "Password Can't be empty")
	private String userPassword;

	private String admin;

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
