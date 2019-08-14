<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Update Comment</title>
	
</head>
<body>
	<h1>Update Comment</h1>
	<form action = "/converge/home.jsp" method = "post">
		<input type = "submit" value = "Home">
	</form>

	<form action = "/converge/Servlet" method = "post">
		Body:<textarea name = "body"><%=request.getParameter("body")%></textarea><br>
		<input type = "hidden" name = "action" value = "UPDATECOMMENT">
		<input type = "hidden" name = "comment" value = "<%=request.getParameter("comment")%>">
		<input type = "submit" value = "Update Comment">
	</form>
</body>
</html>