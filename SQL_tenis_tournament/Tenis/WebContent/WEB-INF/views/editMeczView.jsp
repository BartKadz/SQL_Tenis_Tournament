
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Match</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Edit Match</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/editMecz">
         <input type="hidden" name="idmecz" value="${mecz.idmecz}" />
         <table border="0">
                     <tr>
            <td>Spotkanie</td>
            <td><select name="idSpotkania" value=${mecz.id_spotkania } required>
    <c:forEach items="${spotkania}" var="spotkanie">
        <option value="${spotkanie.idSpotkania}">${spotkanie.idSpotkania}</option>
    </c:forEach>
</select></td>
            </tr>
            <tr>
            <td>Zespol</td>
            <td><select name="idZespolu" value ="${mecz.id_zespolu }" required>
    <c:forEach items="${zespoly}" var="zespol">
        <option value="${zespol.idZespolu}">${zespol.nazwa}</option>
    </c:forEach>
</select></td>
            </tr>
              <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="matches">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>