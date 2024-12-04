<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Managers  List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Managers list</h3>
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Surname</th>  
           <c:if test="${admin == 1}">
           <th>Edit</th>
          <th>Delete</th>
             </c:if>        
       </tr>
       <c:forEach items="${managerowie}" var="manager" >
          <tr>
          <td>${manager.idUzytkownika}</td>
             <td>${manager.imie}</td>
             <td>${manager.nazwisko}</td>
             <c:if test="${admin == 1}">
             <td>
                <a href="editManager?code=${manager.idUzytkownika}">Edit</a>
             </td>
             <td>
                <a href="deleteManager?code=${manager.idUzytkownika}">Delete</a>
             </td>
             </c:if>
          </tr>
       </c:forEach>
    </table>
     	
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>