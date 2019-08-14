<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Categories</title>
	
</head>
<body>
	<h1>Category</h1>
	<form action = "/converge/home.jsp" method = "post">
		<input type = "submit" value = "Home">
	</form>
	<table>
		<c:forEach items = "${posts}" var = "post">
			<tr>
				<td><a href="http://${post.link}"><b><c:out value = "${post.title}"/></b></a></td>
				<td><c:out value = "${post.body}"/></td>
				<td>
					<form action = "/converge/Servlet" method = "post">
						<input type = "hidden" name = "action" value = "VIEWPOST">
						<input type = "hidden" name = "post" value = "${post.UID}">
						<input type = "submit" value = "View Comments">
					</form>
				</td>
				<td>
					<form action = "/converge/createcomment.jsp?post=${post.UID}" method = "post">
						<input type = "hidden" name = "action" value = "CREATECOMMENT">
						<input type = "submit" value = "Create Comment">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	

</body>
</html>