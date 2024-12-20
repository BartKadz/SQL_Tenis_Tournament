
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Manager</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Manager</h3>
 
      <p style="color: red;">${errorString}</p>
 
         <form method="POST" action="${pageContext.request.contextPath}/editManager">
            <input type="hidden" name="idUzytkownika" value="${manager.idUzytkownika}" />
            <table border="0">
               <tr>
                  <td>Id</td>
                  <td style="color:red;">${manager.idUzytkownika} </td>
               </tr>
               <tr>
                  <td>Name</td>
                  <td><input type="text" name="imie" value="${manager.imie}" default = "${manager.imie}" required/></td>
               </tr>
               <tr>
                  <td>Surname</td>
                  <td><input type="text" name="nazwisko" value="${manager.nazwisko}"default = "${manager.nazwisko}" required/></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/managers">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>