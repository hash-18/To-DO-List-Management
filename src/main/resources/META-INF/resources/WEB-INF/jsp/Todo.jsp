<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login page</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="common/navigation.jspf"%>
	<div class="container">
		<h1>Welcome to Add Todos page</h1>

		<pre>${error}</pre>
		<div class="container">
			<form:form method="post" modelAttribute="todo">
			Name <form:input type="text" name="name" path="name"
					required="required" />
				<form:errors path="name" cssClass="text-warning" />
				<br>
			Description <form:input type="text" name="description"
					path="description" required="required" />
				<form:errors path="description" cssClass="text-warning" />
				<br>
			Target Date <form:input type="date" name="targetDate"
					path="targetDate" required="required" />
				<form:errors path="targetDate" cssClass="text-warning" />


				<hr>
				<input type="submit" class="btn btn-success">
			</form:form>
		</div>
	</div>


</body>
</html>