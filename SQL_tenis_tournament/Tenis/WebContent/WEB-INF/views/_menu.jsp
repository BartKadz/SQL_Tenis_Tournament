<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="padding: 5px;">
 
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
    <c:if test="${loginedUser.login==null}">
   <a href="${pageContext.request.contextPath}/login">Login</a>
    |
   <a href="${pageContext.request.contextPath}/register">Register</a>
         </c:if>
   |
   <a href="${pageContext.request.contextPath}/managers">Manager List</a>
   |
   <a href="${pageContext.request.contextPath}/tournaments">Tournaments List</a>
   |
   <a href="${pageContext.request.contextPath}/players">Players List</a>
   <c:if test="${loginedUser.login!=null}">
   |
   <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:if>
  
</div>  