<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/cart.css">
<title>Insert title here</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="content">
		<div class="d-flex py-3">
			<h3>Total Price: $<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${total}" /></h3>
			<a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a>
		</div>
		
		
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price($)</th>
					<th scope="col">Quantity</th>
					<th scope="col">Total</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart_list}" var="o">
				<tr>
					<td>${o.getName()}</td>
					<td>${o.getBrand()}</td>
					<td>${o.getPrice()}</td>					
					
					<td>
						<form action="buy-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="${o.getId()}" class="form-input">
							<div class="form-group d-flex justify-content-between w-30">
								<a class="btn btn-sm btn-decre" href="number-inc-dec?action=dec&id=${o.getId()}"><i class="fas fa-minus-square"></i></a>								 
								<input type="text" name="quantity" class="form-control w-50"  value="${o.getNumber()}" readonly> 
								<a class="btn bnt-sm btn-incre" href="number-inc-dec?action=inc&id=${o.getId()}"><i class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td>$<fmt:formatNumber type = "number" maxFractionDigits = "4" value = "${o.getPrice()*o.getNumber()}" /></td>
					<td><a href="remove-from-cart?id=${o.getId()}" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
			</c:forEach> 	
			</tbody>
		</table>
	</div>
	<div class="cart-footer">
	<jsp:include page="footer.jsp"></jsp:include>	
	</div>	
</body>

</html>