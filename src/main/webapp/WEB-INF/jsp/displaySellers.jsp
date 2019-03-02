<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller List</title>
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
		<jsp:param name="isSeller" value="true" />
		<jsp:param name="show" value ="true"/>
	</jsp:include>
	<div class="divButtonRight">
		<button type="button" class="btn btn-primary" onclick="check()">Approve
			All</button>

	</div>
	<h1>Display Sellers</h1>
	<c:choose>
		<c:when test="${not empty result}">
			<c:forEach items="${result}" var="item">
				<div>

					<h3>${item.companyName}</h3>
					<p>${item.ownerName}</p>
					<p>${item.companyAddress }</p>
					<p>${ item.sellerStatus}</p>
					<c:if test="${item.sellerStatus=='NEED_APPROVAL' }">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" name="products" class="case"
								value="${item.sellerId}">Approve</input>
						</div>
					</c:if>
					<a href="viewSeller.html?sellerId=${item.sellerId}"> <span>Read
							more...</span>
					</a>
				</div>
				<hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
			No seller added to Your Mart.
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
			if (selected.length == 0) {

			} else {

				console.log(selected);
				window.location.href = "approveSellers.html?sellerId="
						+ selected;
			}

		}
		function nextPage() {
			<%System.out.println(request.getQueryString());%>
	var keyword = "<%= request.getParameter("keyword")%>";
	var searchAlias= "<%= request.getParameter("search-alias")%>";
	
	var sort="<%= request.getParameter("sort")%>";
	var ref="${page.currentPage}";
	ref++;
	var path="displaySellers.html?ref="+ ref;
	if(sort!="null"){
		path += "&sort=" +sort;
	}
	if(sort!="null"){
		path += "&sort=" +sort;
	}
	if(keyword!="null"){
		path += "&keyword=" +keyword;
	}if(searchAlias!="null"){
		path+="&search-alias="+searchAlias;
	}
	
			window.location.href = path;
		}
	</script>
</body>
</html>