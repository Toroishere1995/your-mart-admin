package com.learning.yourmartpmp.dto;

/**
 * DTO class for status data.
 * @author ayushsaxena
 *
 */
public class StatusData {
	private String statusMessage;
	private String statusCode;
	private String requestType;
	private String error;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String message) {
		this.statusMessage = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return statusMessage;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestType() {
		return requestType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setErrorMessage(String message) {
		this.statusMessage = message;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
