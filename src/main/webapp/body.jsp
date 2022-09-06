<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="/as3-test2/home">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Category</a></li>
						<li class="breadcrumb-item active" aria-current="#">Sub-category</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<jsp:include page="left.jsp"></jsp:include>

			<div class="col-sm-9">
				<div class="row">
					<c:forEach items="${products}" var="o">
						<div class="col-12 col-md-6 col-lg-4">
							<div class="card">
								<img class="card-img-top" src="${o.src}" alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title show_txt">
										<a href="inforproduct?pid=${o.id}" title="View Product">${o.name}</a>
									</h4>
									<%-- plus --%>
									<p class="card-text show_txt">${o.type}</p>
									<p class="card-text show_txt">${o.description}</p>
									<div class="row">
										<div class="col">
											<p class="btn btn-danger btn-block">${o.price}$</p>
										</div>
										<div class="col">
											<a href="add-to-cart?id=${o.id}" class="btn btn-success btn-block">Add to cart</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>
</body>
</html>