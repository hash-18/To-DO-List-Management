<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<title>List Todos Page</title>

</head>
<body>

	<%@ include file="common/navigation.jspf"%>
	<div class="container">
		<h1>Welcome ${userName}</h1>
		<div>Your todos are</div>
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>description</th>
					<th>Target Date</th>
					<th>Is done</th>
					<th>Action</th>
				</tr>

			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.id}</td>
						<td>${todo.name}</td>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="delete-todo?id=${todo.id}"
							class="btn btn-warning">Delete</a> <a
							href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<a href="add-todos" class="btn btn-success">Add Todos</a>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>