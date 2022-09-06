<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/cart.css">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

</head>
<body>
	<!--begin of menu-->
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="home">PRJ321x</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExampleDefault"
				aria-controls="navbarsExampleDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navbarsExampleDefault">
				<ul class="navbar-nav m-auto">
					<c:if test="${sessionScope.acc.role == 1}">
						<li class="nav-item"><a class="nav-link" href="index.jsp">Manager Account</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Product Management</a></li>
					</c:if>					
					
					<c:if test="${sessionScope.acc != null}">
						<li class="nav-item"><a class="nav-link" href="#">Hello ${sessionScope.acc.name}</a></li>
						<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
					</c:if>
						
						<li class="nav-item"><a class="nav-link" href="home">Home</a></li>
						
					<c:if test="${sessionScope.acc == null}">
						<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
					</c:if>
				</ul>

				<form action="search" method="get" class="form-inline my-2 my-lg-0">
					<div class="input-group input-group-sm">
						<input name="txt" value="${searched}" type="text" class="form-control"
							aria-label="Small" aria-describedby="inputGroup-sizing-sm"
							placeholder="Search...">
						<div class="input-group-append">
							<button type="submit" class="btn btn-secondary btn-number">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<a class="btn btn-success btn-sm ml-3" href="inforcart"> <i
						class="fa fa-shopping-cart"></i> Cart <span
						class="badge badge-light">${cart_list.size()}</span>
					</a>
				</form>
			</div>
		</div>
	</nav>
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">${message}</h1>
			<p class="lead text-muted mb-0">Never too old to get a NEW device!</p>
		</div>
	</section>
	<!--end of menu-->
</body>
</html>