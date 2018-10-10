<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Theater List</title>
<link type="text/css" rel="stylesheet" href="css/design.css"/>
</head>
<body style="background-color: wheat;">
<div>
	<h2>Movie Name: ${movieName}</h2> 
	<br>
	<form>
		<table>
			<tr>
				<th>Theater Name</th>
				<th>Available Tickets</th>
				<th>Date / Time</th>
				<th>Price</th>
				<th>Booking</th>
			</tr>
		<c:forEach items="${theaterList}" var="theater">
			<tr>
				<td>${theater.theaterName}</td>
				<td>${theater.availableTickets}</td>
				<td>${theater.moviePlayTime}</td>
				<td>${theater.ticketPrice}</td>	
				<td><c:if test="${theater.availableTickets == 0}">
					All bookings full
				</c:if>
				<c:if test="${theater.availableTickets != 0}">
					<a href="MovieController?action=Book&theaterId=${theater.theaterId}">Avail Booking</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</table>
		<a href="MovieController?action=fetchName">Back</a>
	</form>
	</div>
</body>
</html>