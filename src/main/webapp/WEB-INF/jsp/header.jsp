<!doctype html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
<title>Hello, world!</title>
<style type="text/css">
.divButtonRight {
	display: block;
	float: right;
	margin: auto;
	padding: 10px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="background-color: #e3f2fd">
		<a class="navbar-brand">YourMart Admin Panel</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<c:if test="${param.show }">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="displaySellers.html">Sellers <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="displayProducts.html">Products</a></li>
					<li class="nav-item"><a class="nav-link"
						href="displayCategory.html">Categories</a></li>

				</ul>
				<c:choose>
					<c:when test="${param.navSearch }">
						<c:choose>
							<c:when test="${param.isSeller}">


								<div style="float: right;">
									<form class="form-inline my-2 my-lg-0" method="get"
										action="displaySellers.html">
										<select class="selectpicker" data-style="btn-danger"
											data-max-options="1" name="search-alias">
											<optgroup label="Seller Status">
												<option value="ALL">All</option>
												<option value="NEED_APPROVAL">Need Approval</option>
												<option value="APPROVED">Approved</option>
												<option value="REJECTED">Rejected</option>
											</optgroup>

										</select> <input class="form-control mr-sm-2" type="search"
											id="searchField" placeholder="Search" aria-label="Search"
											onchange="checkInput(this)" name="keyword">
										<button class="btn btn-outline-success my-2 my-sm-0"
											id="searchButton" type="submit" disabled="disabled">Search</button>
									</form>
									<div>
										<%
											System.out.println("Keyword: " + request.getParameter("keyword"));
																System.out.println("Search : " + request.getParameter("search-alias"));
										%>
										<form class="form-inline my-2 my-lg-0" method="get"
											action="displaySellers.html">
											<input type="hidden" name="keyword"
												value="<%=request.getParameter("keyword")%>" /> <input
												type="hidden" name="search-alias"
												value="<%=request.getParameter("search-alias")%>" /> <span
												id="sort_by_text" class="a-size-base">Sort by&nbsp;</span> <select
												class="selectpicker" onchange="this.form.submit()"
												data-style="btn-primary" name="sort">
												<option value="none">No Sort</option>
												<option value="sellerId">Seller Id</option>
												<option value="registrationDate">Registration Time</option>

											</select>
										</form>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div style="float: right;">
									<form class="form-inline my-2 my-lg-0" method="get"
										action="displayProducts.html">
										<select class="selectpicker" data-style="btn-danger"
											data-max-options="1" name="search-alias">
											<option value="ALL">All</option>
											<optgroup label="Product Status">

												<option value="productStatus-NEW">New</option>
												<option value="productStatus-APPROVED">Approved</option>
												<option value="productStatus-REJECT">Rejected</option>
												<option value="productStatus-REVIEW">Review</option>
											</optgroup>
											<optgroup label="Seller Company">

												<c:forEach items="${sellers}" var="role">

													<option value="sellerId-${role.sellerId}">${role.companyName}</option>

												</c:forEach>

											</optgroup>
											<optgroup label="Category">

												<c:forEach items="${categories}" var="role">

													<option value="productCategory-${role.categoryName}">${role.categoryName}</option>

												</c:forEach>

											</optgroup>
										</select> <input class="form-control mr-sm-2" type="search"
											id="searchField" placeholder="Search" aria-label="Search"
											onchange="checkInput(this)" name="keyword">
										<button class="btn btn-outline-success my-2 my-sm-0"
											id="searchButton" type="submit" disabled="disabled">Search</button>
									</form>
									<div>

										<form class="form-inline my-2 my-lg-0" method="get"
											action="displayProducts.html">
											<input type="hidden" name="keyword"
												value="<%=request.getParameter("keyword")%>" /> <input
												type="hidden" name="search-alias"
												value="<%=request.getParameter("search-alias")%>" /> <span
												id="sort_by_text" class="a-size-base">Sort by&nbsp;</span> <select
												class="selectpicker" onchange="this.form.submit()"
												data-style="btn-primary" name="sort">
												<option value="none">No Sort</option>
												<option value="productMrp">MRP</option>
												<option value="productSsp">SSP</option>
												<option value="productYmp">YMP</option>
											</select>
										</form>
									</div>
								</div>
							</c:otherwise>
						</c:choose>

					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

			</div>

			<div class="divButtonRight">
				<a class="btn btn-primary" href="logout.html">Log Out</a>


			</div>
		</c:if>
	</nav>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		function changeDropDownName(elem) {
			document.getElementById("navbarDropdown").innerText = elem.value;
		}
		function checkInput(elem) {
			if (elem.value.length != 0) {
				document.getElementById("searchButton").disabled = false;
			} else {
				document.getElementById("searchButton").disabled = true;
			}
		}
		function sendQuery() {
			let
			val = document.getElementById("searchField").value;
			if (document.getElementById("navbarDropdown").innerText != "Filter") {
				let
				valFilter = document.getElementById("navbarDropdown").innerText;
				return "displaySellers.html?keyword=" + val + "&search-alias="
						+ valFilter;
			} else {
				window.alert(val);
				return "displaySellers.html?keyword=" + val;
			}
		}
	</script>
</body>
</html>