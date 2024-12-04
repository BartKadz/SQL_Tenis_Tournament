<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tournaments List</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Tournaments  list</h3>
	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Id</th>
			<th>Date</th>
			<th>Place</th>
			<th>Court Type</th>
			<th>Manager</th>
			<th>Games</th>
				<th>Edit</th>
				<th>Delete</th>
		</tr>
		<c:forEach items="${turnieje}" var="turniej">
			<tr>
				<td>${turniej.idTurnieju}</td>
				<td>${turniej.data}</td>
				<td>${turniej.miejsce}</td>
				<td>${turniej.nawierzchnia}</td>
				<td>${turniej.manager}</td>
				<td><a href="games?code=${turniej.idTurnieju}">Games</a>
					</td>
				<c:if test="${turniej.czy_moj ==1}">
					<td><a href="editTournament?code=${turniej.idTurnieju}">Edit</a>
					</td>
					<td><a href="deleteTournament?code=${turniej.idTurnieju}">Delete</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
		    <c:if test="${loginedUser.login!=null}">
		    		<a href="createTournament">Create Tournament</a>
         </c:if>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>