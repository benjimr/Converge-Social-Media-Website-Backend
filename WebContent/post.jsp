<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Post</title>
	
</head>
<body>
	<h1>Post</h1>
	<form action = "/converge/home.jsp" method = "post">
		<input type = "submit" value = "Home">
	</form>
	
	<table>
		<c:forEach items = "${comments}" var = "comment">
			<tr>
				<td><c:out value = "${comment.body}"/></td>
				<td><c:out value = "${comment.creationDate}"/></td>
				
				<c:if test="${sessionScope.userID == comment.creator}">
					<td>
						<form action = "/converge/updatecomment.jsp?comment=${comment.UID}&body=${comment.body}" method = "post">
							<input type = "submit" value = "Edit Comment">				
						</form>
					</td>
				</c:if>
				

			</tr>
		</c:forEach>
	</table>
</body>
</html>