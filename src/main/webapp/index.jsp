<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/PRJ321x-A2/css/index.css" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header class="header">
		<a href="">Welcome ${sessionScope.acc.name}</a>

		<div class="logout">
			<a href="logout" class="btn btn-primary">Logout</a>
		</div>
	</header>

	<aside>
		<ul>
			<li><a href="home">Home</a></li>
			<li><a href="">Dashboard</a></li>
			<li><a href="">Staff Manager</a></li>
		</ul>
	</aside>

	<div class="content">
		
		<h2 class="page-title">Members of the team</h2>

		<table>
			<thead>
				<th>Mail</th>
				<th>Name</th>
				<th>Adress</th>
				<th>Phone</th>
				<th colspan="3">Action</th>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Member 1</td>
					<td>Class 1</td>
					<td>123</td>
					<td><a href="#" class="edit">edit</a></td>
					<td><a href="#" class="delete">delete</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Member 2</td>
					<td>Class 2</td>
					<td>456</td>
					<td><a href="#" class="edit">edit</a></td>
					<td><a href="#" class="delete">delete</a></td>
				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>