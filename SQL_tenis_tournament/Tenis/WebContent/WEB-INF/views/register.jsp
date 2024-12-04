
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Register</h3>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/register">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="login" value= "${user.login}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="haslo" value= "${user.haslo}" /> </td>
            </tr>
             <tr>
               <td>Name</td>
               <td><input type="text" name="imie" value= "${user.imie}" /> </td>
            </tr>
             <tr>
               <td>Surname</td>
               <td><input type="text" name="nazwisko" value= "${user.nazwisko}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>