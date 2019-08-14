<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<p>HOME PAGE</p>
	
	<% if(session.getAttribute("userID") == null)
	{%>
		<form action = "/converge/login.html" method = "post">
			<input type = "submit" value = "Login">
		</form>
		
		<form action = "/converge/register.html" method = "post">
			<input type = "submit" value = "Register">
		</form>
	<%}
	else 
	{%>
		<h3>Welcome to Converge <%= session.getAttribute("username") %></h3>
		<form action = "/converge/Servlet" method = "Post">
			<input type = "hidden" name = "action" value = "LOGOUT">
			<input type = "submit" value ="Logout">
		</form>
	
		<form action = "/converge/Servlet" method = "post">
			<input type = "hidden" name = "action" value = "VIEWCATEGORIES">
			<input type = "submit" value = "View Categories">
		</form>
	<%} %>
	

</body>
</html>