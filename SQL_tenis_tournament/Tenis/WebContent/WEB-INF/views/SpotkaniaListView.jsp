<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Games List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Games list</h3>
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Set1</th>
          <th>Set2</th>
          <th>Set3</th>
          <th>Set4</th>
          <th>Set5</th>
          <th>Player1</th>
          <th>Player2</th>    
           <c:if test="${admin == 1}">
           <th>Edit</th>
          <th>Delete</th>
             </c:if>         
       </tr>
       <c:forEach items="${spotkania}" var="spotkanie" >
          <tr>
             <td>${spotkanie.idSpotkania}</td>
             <td>${spotkanie.set1}</td>
             <td>${spotkanie.set2}</td>
             <td>${spotkanie.set3}</td>
             <td>${spotkanie.set4}</td>
             <td>${spotkanie.set5}</td>
             <td>${spotkanie.zawodnik1}</td>
             <td>${spotkanie.zawodnik2}</td>
             <c:if test="${admin == 1}">
             <td>
                <a href="editSpotkanie?code=${spotkanie.idSpotkania}&tour=${kod}">Edit</a>
             </td>
             <td>
                <a href="deleteSpotkanie?code=${spotkanie.idSpotkania}&tour=${kod}">Delete</a>
             </td>
             </c:if>
          </tr>
       </c:forEach>
    </table>
     <c:if test="${admin == 1}">
                <a href="createSpotkanie?tour=${kod}">Create Match Details</a>
          		</c:if>
          		
          		 <h3>Tournament ranking</h3>
     <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Player</th>
          <th>Wins</th> 
          <th>Loses</th>  
          <th>Won sets</th>  
          <th>Lost sets</th>   
          <th>Won gems</th>  
          <th>Lost gems</th>  
          <th>Won/Lost gems ratio</th>       
       </tr>
       <c:forEach items="${rankingi}" var="ranking" >
          <tr>
             <td>${ranking.idZawodnika}</td>
             <td>${ranking.zawodnik}</td>
             <td>${ranking.wygrane}</td>
             <td>${ranking.przegrane}</td>
             <td>${ranking.wygrane_sety}</td>
             <td>${ranking.przegrane_sety}</td>
             <td>${ranking.wygrane_gemy}</td>
             <td>${ranking.przegrane_gemy}</td>
             <td>${ranking.stosunek_gemow}</td>
             
          </tr>
       </c:forEach>
    </table>    	
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>