
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Tournament</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Edit Tournament</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/editTournament">
      <input type="hidden" name="idTurnieju" value="${turniej.idTurnieju}" />
         <table border="0">
            <tr>
               <td>Date</td>
               <td><input type="date" name="data" value="${turniej.data}" default="${turniej.data}"  required/></td>
            </tr>
            <tr>
               <td>Place</td>
               <td><input type="text" name="miejsce" value="${turniej.miejsce}" default="${turniej.miejsce}"required/></td>
            </tr>
             <tr>
               <td>Court Type</td>
               <td><input type="text" name="nawierzchnia" value="${turniej.nawierzchnia}"default="${turniej.nawierzchnia}" required/></td>
            </tr>
           		    <c:if test="${admin==1}">
		    		<tr>
		    		 <tr>
            <td>Manager</td>
            <td><select name="idUzytkownika" required>
        <c:forEach items="${managerowie}" var="manager">
    <c:choose> 
  <c:when test="${manager.idUzytkownika == turniej.idManagera}">
   <option value="${manager.idUzytkownika}" selected>${manager.imie} ${manager.nazwisko} </option>
  </c:when>
  <c:otherwise>
   <option value="${manager.idUzytkownika}">${manager.imie} ${manager.nazwisko} </option>
  </c:otherwise>
</c:choose>
        
    </c:forEach>
</select></td>
            </tr>
		    		</tr>
         </c:if>
              <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="tournaments">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>