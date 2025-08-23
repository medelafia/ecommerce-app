<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%

	String adminName = null  ;  
	
	HttpSession httpSession =  request.getSession() ; 
	
	
	if(httpSession != null ) { 
		
		if( httpSession.getAttribute("commercial") != null ) { 
			Admin commercialAuthenticated = (Admin)httpSession.getAttribute("commercial") ; 
			adminName = commercialAuthenticated.getNom() ; 
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar">
	  <div class="container d-flex align-items-center justify-content-between py-3 px-5">
	  	<div style="font-size: 1.5rem;" class="text-secondary">Welcome , <%=adminName%></div>
	  	
	  	<form class="d-flex" action="/logout" method="post">
	         <button class="btn text-secondary" type="submit"><i class="fa-solid fa-right-from-bracket"></i></button>
		 </form>
	  </div>
	</nav>
</body>
</html>