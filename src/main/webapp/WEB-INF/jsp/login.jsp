<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Here</title>
<style>
.error {
	color: #ff0000
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<jsp:include page="header.jsp">
		<jsp:param name="show" value="false" />
</jsp:include>
	<center>
		<%
			if (null != request.getAttribute("errormessage")) {
		%>
		<%
			out.println(request.getAttribute("errormessage"));

			}
		%>
	</center>
	<center>

		<form:form commandName="loginAdmin">

			<table cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Enter Information to Login</th>
					</tr>
				</thead>
				<tbody>
					<tr>

						<td>Username</td>
						<td><form:input path="userName" /></td>
						<td><form:errors path="userName" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:password path="userPassword" /></td>
						<td><form:errors path="userPassword" cssClass="error" /></td>
					</tr>
					<tr>
						<td></td>
						
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="submit" /></td>
					</tr>
					
				</tbody>
			</table>
<div class="g-recaptcha" data-sitekey="6Ldsn3gUAAAAAJmU5AgDR3Q-aGJlhr5IQXvGNApZ"></div>
		</form:form>
		<table cellpadding="5">
			<tbody>
		</table>

	</center>
</body>
</html>