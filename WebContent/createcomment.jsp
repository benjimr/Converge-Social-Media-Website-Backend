<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create Comment</title>
	
</head>
<body>
	<h1>Create Comment</h1>
	<form action = "/converge/home.jsp" method = "post">
		<input type = "submit" value = "Home">
	</form>

	<form action = "/converge/Servlet" method = "post">
		Body:<textarea name = "body"></textarea><br>
		<input type = "hidden" name = "action" value = "CREATECOMMENT">
		<input type = "hidden" name = "post" value = "<%=request.getParameter("post")%>">
		<input type = "hidden" name = "user" value = "<%=session.getAttribute("userID")%>">
		<input type = "submit" value = "Create Comment">
	</form>
</body>
</html>