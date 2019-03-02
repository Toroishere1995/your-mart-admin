<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
		
		<jsp:param name="show" value="true" />
	</jsp:include>

	<h1>Display Categories</h1>
	<div class="divButtonRight">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModal" data-whatever="Add">Add Category</button>

	</div>
	<c:choose>
		<c:when test="${not empty result}">
			<c:forEach items="${result}" var="item">
				<div class="container">

					<div class="divLeft">

						<h3>${item.categoryName}</h3>
						<c:choose>
							<c:when test="${item.categoryProductsCount =='0'}">
								<div class="divLeft">
									<p>Sorry, YourMart don't have any items under this
										category.</p>
								</div>
								<div class="divRight">
									<button type="button" class="btn btn-outline-success"
										data-toggle="modal" data-target="#exampleModal"
										data-whatever="Edit" data-name="${item.categoryName }" data-id="${item.categoryId }" data-products="${item.categoryProductsCount}">Edit</button>
									<a href="deleteCategory.html?categoryId=${item.categoryId}" class="btn btn-outline-success" role="button" aria-pressed="true">Delete</a>
									
								</div>
							</c:when>
							<c:otherwise>
								<div class="divLeft">
									<p>YourCart can provide total of ${ item.categoryProductsCount}
										products under this category.</p>
								</div>
								<div class="divRight">

									<button type="button" class="btn btn-outline-success"
										data-toggle="modal" data-target="#exampleModal"
										data-whatever="Edit" data-name="${item.categoryName }" data-id="${item.categoryId }" data-products="${item.categoryProductsCount}">Edit</button>

								</div>
							</c:otherwise>
						</c:choose>


					</div>
				</div>
				<hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
			No categories alloted at Your Mart.
		</c:otherwise>
	</c:choose>


	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="exampleModalLabel"></h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form method="post" commandName="category" action="manageCategory.html">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Category
								Name:</label> <form:input path="categoryName" type="text" class="form-control" id="category-name"
								name="category-name"/>
								<form:hidden path="categoryId" class="form-control"
								id="category-id" name="category-id"  />
								<form:hidden path="categoryProductsCount" class="form-control"
								id="category-product-count" name="category-product-count" />
						</div>
						<div class="form-group">
							<input type="hidden" class="form-control" id="action-type"
								name="action-type">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">

		$('#exampleModal').on('shown.bs.modal', function(event) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			var recipient = button.data('whatever') // Extract info from data-* attributes
			var itemCategory = button.data('name')
			var categoryId = button.data('id')
			var categoryProductsCount=button.data('products')
			
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this)
			if (recipient == 'Edit') {
				document.getElementById("category-name").value=itemCategory;
				document.getElementById("action-type").value="edit";
				document.getElementById("category-id").value = categoryId;
				document.getElementById("category-product-count").value =categoryProductsCount;

			} else {
				document.getElementById("category-name").value='';
				document.getElementById("action-type").value="add";
				document.getElementById("category-id").value = 0;
				document.getElementById("category-product-count").value =0;

			}
		})
		
		
		
			
			
	</script>
</body>
</html>