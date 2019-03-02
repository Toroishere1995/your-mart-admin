<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Information</title>
<style type="text/css">
.container {
	margin: 20px;
	padding: 10px;
}

.form {
	margin: auto;
	padding: 10px;
}

.divLeft {
	display: block;
	float: left;
}

.divRight {
	display: block;
	margin-left: 35%;
}

.w-1020 {
	margin: auto;
	width: 300px;
}

.carousel-control-prev-icon, .carousel-control-next-icon {
	height: 100px;
	width: 100px;
	outline: black;
	background-size: 100%, 100%;
	border-radius: 50%;
	border: 1px solid black;
	background-image: none;
}

.carousel {
	position: relative;
	width: 70%;
	margin: auto;
}

.carousel-control-next-icon:after {
	content: '>';
	font-size: 55px;
	color: red;
}

.carousel-control-prev-icon:after {
	content: '<';
	font-size: 55px;
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="navSearch" value="false" />
		<jsp:param name="show" value="true" />
	</jsp:include>

	<c:choose>
		<c:when test="${not empty productImages}">



			<div id="carouselExampleIndicators" class="carousel slide"
				data-ride="carousel">

				<div class="carousel-inner">

					<c:forEach items="${productImages}" var="productImage"
						varStatus="loop">
						<c:choose>
							<c:when test="${loop.first}">
								<div class="carousel-item active">
									<img class="d-block w-1020" src="${productImage.image }"
										alt="Image Not fetched" height="300" width="300">
								</div>
							</c:when>
							<c:otherwise>
								<div class="carousel-item">
									<img class="d-block w-1020" src="${productImage.image }"
										alt="Image Not fetched" height="300" width="300">
								</div>
							</c:otherwise>
						</c:choose>

					</c:forEach>

				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</c:when>

	</c:choose>
	<div class="container">
		<div class="divLeft">
			<img src="${product.productPrimaryImage }"
				class="img-thumbnail" alt="..." height="200" width="200">

		</div>
		<div class="divRight">
			<table class="table table-striped">

				<tbody>
					<tr>
						<th scope="row">Product Name</th>
						<td>${ product.productName}</td>

					</tr>
					<tr>
						<th scope="row">Product Short Description</th>
						<td>${ product.productShortDescription}</td>

					</tr>
					<tr>
						<th scope="row">Product Category</th>
						<td>${ product.productCategory}</td>

					</tr>
					<tr>
						<th scope="row">Product MRP</th>
						<td>${ product.productMrp}</td>
					</tr>
					<tr>
						<th scope="row">Product YMP</th>
						<td>${ product.productYmp}</td>
					</tr>
					<tr>
						<th scope="row">Product Status</th>
						<td>${ product.productStatus}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<form:form commandName="product" action="changeProductStatus.html">
		<form:hidden path="productId" />

		<label>Change Status : </label>
		<form:radiobutton path="productStatus" value="NEW" label="NEW"
			onchange="enableButton(this)" />
		<form:radiobutton path="productStatus" value="REJECT" label="REJECT"
			onchange="enableButton(this)" />
		<form:radiobutton path="productStatus" value="APPROVED"
			label="APPROVE" onchange="enableButton(this)" />
		<form:radiobutton path="productStatus" value="REVIEW" label="REVIEW"
			onchange="enableButton(this)" />
		<br>
		<form:textarea path="productComment" name="commentName" id="commentId"
			cols="50" disabled="true" onchange="checkNotEmpty(this)" />

		<input id="applyChange" type="submit" value="Apply"
			name="changeStatus" disabled="disabled" />
	</form:form>

	<script type="text/javascript">
		function enableButton(elem) {

			if (elem.value != "${product.productStatus}") {
				if (elem.value != "REJECT") {
					document.getElementById("applyChange").disabled = false;
					document.getElementById("commentId").disabled = false;
				} else {
					document.getElementById("commentId").disabled = false;
					if (document.getElementById("commentId").value.length != 0
							&& document.getElementById("commentId").value != "${product.productComment}") {
						document.getElementById("applyChange").disabled = false;
					} else {
						document.getElementById("applyChange").disabled = true;
					}
				}
			} else {
				document.getElementById("applyChange").disabled = true;
				document.getElementById("commentId").disabled = true;
			}
		}
		function checkNotEmpty(elem) {
			if (elem.value.length != 0) {
				document.getElementById("applyChange").disabled = false;
			} else {
				document.getElementById("applyChange").disabled = true;
			}
		}
	</script>
</body>
</html>