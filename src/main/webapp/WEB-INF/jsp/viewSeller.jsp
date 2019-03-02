<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Seller</title>
</head>
<body>
<jsp:include page="header.jsp">
		<jsp:param name="navSearch" value="false" />
		<jsp:param name="show" value="true" />
	</jsp:include>
	<center>
		
			<table cellpadding="5">
				<tbody>

					<tr>

						<td>Company Name</td>
						<td>${ seller.companyName}</td>

					</tr>
					<tr>

						<td>Owner Name</td>
						<td>${ seller.ownerName}</td>
					</tr>

					<tr>

						<td>Date of Registration</td>
						<td>${ seller.registrationDate}</td>
					</tr>
					<tr>

						<td>Company Address</td>
						<td>${ seller.companyAddress}</td>
					</tr>

					<tr>

						<td>Seller Email</td>
						<td>${ seller.sellerEmail}</td>
					</tr>


					<tr>

						<td>Seller Telephone</td>
						<td>${ seller.sellerTelephone}</td>
					</tr>


					<tr>

						<td>Seller GSTN</td>
						<td>${ seller.sellerGst}</td>
					</tr>


					<tr>

						<td>Seller Status</td>
						<td>${seller.sellerStatus }</td>

						</tr>
					<tr>

					</tr>
				</tbody>
			</table>
			<br>
<form:form commandName="seller" action="changeSellerStatus.html">
<form:hidden path="sellerId"/>
<label>Change Status : </label>
<form:radiobutton path="sellerStatus" value="APPROVED"
								label="APPROVED" /> <form:radiobutton path="sellerStatus"
								value="NEED_APPROVAL" label="NEED_APPROVAL" /> <form:radiobutton
	
								path="sellerStatus" value="REJECTED" label="REJECTED" />
<input type="submit" value="Save" name="changeStatus" />
</form:form>

	</center>
</body>
</html>