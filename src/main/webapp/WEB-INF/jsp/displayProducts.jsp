<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>
<style type="text/css">
.container {
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

.divButtonRight {
	display: block;
	float: right;
	margin: auto;
	padding: 10px;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp">
	<jsp:param name="navSearch" value="true" />
		<jsp:param name="isSeller" value="false" />
		<jsp:param name="show" value="true" />
	</jsp:include>
	<div class="divButtonRight">
		<button type="button" class="btn btn-primary" onclick="check()">Approve
			All</button>

	</div>
	<h1>Display Products</h1>
	<c:choose>
		<c:when test="${not empty result}">
			<c:forEach items="${result}" var="item">

				<div class="container">
					<div class="divLeft">
						<img src="${item.productPrimaryImage }"
							class="img-thumbnail" alt="..." height="200" width="200">
					</div>
					<div class="divRight">

						<h3>${item.productName}</h3>
						<p>${item.sellerProductCode}</p>
						<p>${item.productShortDescription}</p>
						<p>${item.productCategory}</p>

						<c:if test="${item.productStatus!='APPROVED' }">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" name="products" class="case"
									value="${item.productId}">Approve</input>
							</div>
						</c:if>
						<a href="viewProduct.html?productId=${item.productId}"> <span>Read
								more...</span>
						</a>
					</div>
				</div>
				<hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
			No product available at Your Mart.
		</c:otherwise>
	</c:choose>
	<c:if test="${page.nextPage == false}">
		<div class="divButtonRight">
			<button type="button" class="btn btn-primary" onclick="nextPage()">Next</button>
		</div>
	</c:if>
	<script type="text/javascript">
		
		function check() {
			var selected = [];
			$("input[type='checkbox']:checked").each(function() {
				selected.push($(this).val());
			});
			if(selected.length==0){
				
			}else{
				
				console.log(selected);
				window.location.href= "approveProducts.html?productId="+selected;
			}
			
		}
		function nextPage() {
			
			var keyword = "<%=request.getParameter("keyword")%>";
			var searchAlias= "<%=request.getParameter("search-alias")%>";
			
			var sort="<%=request.getParameter("sort")%>";
			var ref = "${page.currentPage}";
			ref++;
			var path = "displayProducts.html?ref=" + ref;
			if (sort != "null") {
				path += "&sort=" + sort;
			}
			if (sort != "null") {
				path += "&sort=" + sort;
			}
			if (keyword != "null") {
				path += "&keyword=" + keyword;
			}
			if (searchAlias != "null") {
				path += "&search-alias=" + searchAlias;
			}

			window.location.href = path;
		}
	</script>
</body>
</html>