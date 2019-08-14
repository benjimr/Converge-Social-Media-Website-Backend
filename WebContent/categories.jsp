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
	<h1>Categories</h1>
	<form action = "/converge/home.jsp" method = "post">
		<input type = "submit" value = "Home">
	</form>
	<table>
		<c:forEach items = "${categories}" var = "cat">
			<tr>
				<td>Category: <c:out value = "${cat.title}"/></td>
				<td>Description: <c:out value = "${cat.description}"/></td>
				<td>
					<form action = "/converge/Servlet" method = "post">
						<input type = "hidden" name = "action" value = "VIEWCATEGORY">
						<input type = "hidden" name = "category" value = "${cat.UID}">
						<input type = "submit" value = "View Posts">
					</form>
				</td>

				<td>
					<form action = "/converge/createpost.jsp?category=${cat.UID}" method = "post">
						<input type = "hidden" name = "action" value = "CREATEPOST">
						<input type = "submit" value = "Create Post">
 					</form>
 				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>