<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie List Page</title>
<link type="text/css" rel="stylesheet" href="css/design.css"/>
<script src="js/validation.js"></script>
</head>
<body style="background-color: wheat">
	<div>
	<form action="MovieController" method="post" onsubmit="return validate()">
		Select Movie Name: <select name="movieName">
			<option value="">Pls Select Movie Name</option>	
			<c:forEach items="${movieNames}" var="movieName">
				<option value="${movieName}">${movieName}</option>
			</c:forEach>
		</select> <BR>
		<input type="submit" value="Search" name="action">
	</form>
	</div>
</body>
</html>