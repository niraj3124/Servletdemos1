<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Now Page</title>
<link type="text/css" rel="stylesheet" href="css/design.css"/>
<script src="js/validation.js"></script>
</head>
<body style="background-color: wheat; ">
	<div>
	<h2>Movie Name: ${movieName}</h2>
	<br>
	<form method="post" action="MovieController" onsubmit="return check()">
		Theater: ${theaterDetails.theaterName} <BR>
		Movie Play Time:${theaterDetails.moviePlayTime}<BR>
		Price per Ticket: ${theaterDetails.ticketPrice}<BR>
		Available Tickets: <input type="text" name="avTickets" value="${theaterDetails.availableTickets}" readonly="readonly" /><BR>
		Enter No. of.Tickets to Book: <input type="text" name="quantity" /><BR>
		<input type="submit" value="ConfirmBook" name="action"/> 
		<a href="MovieController?action=Search&movieName=${movieName}">Cancel Booking</a>
	</form>
	</div>
</body>
</html>