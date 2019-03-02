package com.learning.yourmartpmp.dto;
/**
 * DTO class for response data.
 * @author ayushsaxena
 *
 */
public class ResponseDataCredentialsDto {

	private Object data;

	private StatusData status;

	 private PaginationData paginationDetails;

	public Object getData() {
		return data;
	}

	public StatusData getStatus() {
		return status;
	}

	 public PaginationData getPaginationDetails() {
	 return paginationDetails;
	 }

	public void setData(Object data) {
		this.data = data;
	}

	public void setStatus(StatusData status) {
		this.status = status;
	}

	public void setPaginationDetails(PaginationData paginationDetails) {
		this.paginationDetails = paginationDetails;
	}

}
