
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Player</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Player</h3>
 
      <p style="color: red;">${errorString}</p>
 
         <form method="POST" action="${pageContext.request.contextPath}/editPlayer">
            <input type="hidden" name="idZawodnika" value="${zawodnik.idZawodnika}" />
            <table border="0">
               <tr>
                  <td>ID</td>
                  <td style="color:red;">${zawodnik.idZawodnika} </td>
               </tr>
               <tr>
                  <td>Name</td>
                  <td><input type="text" name="imie" value="${zawodnik.imie}" default = "${zawodnik.imie}" required/></td>
               </tr>
               <tr>
                  <td>Surname</td>
                  <td><input type="text" name="nazwisko" value="${zawodnik.nazwisko}"default = "${zawodnik.nazwisko}" required/></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/players">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>