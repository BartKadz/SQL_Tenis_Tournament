<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Players List</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Players list</h3>
	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Surname</th>
			<c:if test="${admin ==1}">
				<th>Edit</th>
				<th>Delete</th>
			</c:if>
		</tr>
		<c:forEach items="${zawodnicy}" var="zawodnik">
			<tr>
				<td>${zawodnik.idZawodnika}</td>
				<td>${zawodnik.imie}</td>
				<td>${zawodnik.nazwisko}</td>
				<c:if test="${admin ==1}">
					<td><a href="editPlayer?code=${zawodnik.idZawodnika}">Edit</a>
					</td>
					<td><a href="deletePlayer?code=${zawodnik.idZawodnika}">Delete</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${admin ==1}">
		<a href="createPlayer">Create Player</a>
	</c:if>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>