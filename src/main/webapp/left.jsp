<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-3">
				<div class="card bg-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-list"></i> Categories
					</div>
					<ul class="list-group category_block">
						<c:forEach items="${categories}" var="o">
							<li class="list-group-item text-white ${cfilter == o.brand ? 'active':''}"><a href="category?cbrand=${o.brand}">${o.brand}</a></li>
						</c:forEach>

					</ul>
				</div>
				<div class="card bg-light mb-3">
					<div class="card-header bg-success text-white text-uppercase">Last
						product</div>
					<div class="card-body">
						<img class="img-fluid" src="${lastProduct.src}" />
						<h5 class="card-title">${lastProduct.name}</h5>
						<p class="card-text">${lastProduct.description}</p>
						<p class="bloc_left_price">${lastProduct.price}$</p>
					</div>
				</div>
			</div>
</body>
</html>