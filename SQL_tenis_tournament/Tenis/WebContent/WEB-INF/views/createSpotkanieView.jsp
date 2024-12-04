
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Game Details</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Create Game Details</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createSpotkanie">
                  <input type="hidden" name="idTurnieju" value="${kod}"/>
         <table border="0">
            <tr>
               <td>Set1</td>
               <td><input type="text" name="set1" value="${spotkanie.set1}" required/></td>
            </tr>
              <tr>
               <td>Set2</td>
               <td><input type="text" name="set2" value="${spotkanie.set2}" required/></td>
            </tr>
              <tr>
               <td>Set3</td>
               <td><input type="text" name="set3" value="${spotkanie.set3}" required/></td>
            </tr>
              <tr>
               <td>Set4</td>
               <td><input type="text" name="set4" value="${spotkanie.set4}" /></td>
            </tr>
              <tr>
               <td>Set5</td>
               <td><input type="text" name="set5" value="${spotkanie.set5}" /></td>
            </tr>
            <tr>
               <td>Player 1</td>
               <td><select name="idZawodnika1" required>
    <c:forEach items="${zawodnicy}" var="zawodnik">
        <option value="${zawodnik.idZawodnika}">${zawodnik.imie} ${zawodnik.nazwisko} </option>
    </c:forEach>
</select></td>
            </tr>
            <tr>
               <td>Player 2</td>
              <td><select name="idZawodnika2" required>
    <c:forEach items="${zawodnicy}" var="zawodnik">
        <option value="${zawodnik.idZawodnika}">${zawodnik.imie} ${zawodnik.nazwisko} </option>
    </c:forEach>
</select></td>
            </tr>
              <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="games?code=${kod}">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>