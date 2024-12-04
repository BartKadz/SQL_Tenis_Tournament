
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Tournament</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Create Tournament</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createTournament">
         <table border="0">
            <tr>
               <td>Data</td>
               <td><input type="date" name="data" value="${product.data}" required/></td>
            </tr>
            <tr>
               <td>Place</td>
               <td><input type="text" name="miejsce" value="${product.miejsce}" required/></td>
            </tr>
             <tr>
               <td>Court Type</td>
               <td><input type="text" name="nawierzchnia" value="${product.nawierzchnia}" required/></td>
            </tr>
            <tr>
            <td>Manager</td>
            <td><select name="idUzytkownika" required>
    <c:forEach items="${managerowie}" var="manager">
        <option value="${manager.idUzytkownika}">${manager.imie} ${manager.nazwisko}</option>
    </c:forEach>
</select></td>
            </tr>
            
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