<%@page import="dao.DaoOrder"%>
<%@page import="utils.SessionUtils"%>
<%@page import="model.Admin"%>
<%@page import="model.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
	Client client = (Client)SessionUtils.getSessionAttribute(request, "client") ; 
	Admin admin = (Admin)SessionUtils.getSessionAttribute(request, "commercial") ; 
	String clientName = client == null ? null : client.getNom() ; 
	String adminName = admin == null ? null :  admin.getNom()  ;  
	Long nbOrder  = 0l ; 
	if(client!=null) { 
		DaoOrder daoOrder = new DaoOrder() ;  
		nbOrder = daoOrder.getOrdersCountByClientId(client.getId()); 
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
		<nav class="navbar navbar-expand-lg border-bottom py-3">
	  <div class="container">
	    <a class="navbar-brand" href="/index">E-commerce Space</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active ms-3" aria-current="page" href="/index"><i class="fa-solid fa-house me-2"></i>Home</a>
	        </li>
	        
	        <%if(clientName != null || adminName != null ) {%>
	        	<%if(clientName != null ) { %> 
			    	<li class="nav-item">
			          <a class="nav-link" href="/products" >
			            <i class="fa-solid fa-bag-shopping me-2"></i>products
			          </a>
			        </li>
			        <li class="nav-item">
				        <a class="nav-link" href="/orders" >
				            <i class="fa-regular fa-rectangle-list me-2"></i>order(<%=nbOrder %>)
				         </a>
			         </li>
		    	<%} else { %>
		    		<li class="nav-item">
			          <a class="nav-link" href="/admin/dashboard" >
			            Dashboard
			          </a>
			        </li>
		    	<%} %>
		    <%} else { %>
		    	<li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Login
		          </a>
		          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <li><a class="dropdown-item" href="/login?type=client">client</a></li>
		            <li><a class="dropdown-item" href="/login?type=commercial">admin</a></li>
		          </ul>
		        </li>
		    <%}%>
	      </ul>
	      <form class="d-flex" action="/products">
	         <input class="form-control" type="search" placeholder="Search" aria-label="Search" name="search">
	         <button class="btn text-primary text-bold" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
		  </form>
		  <%if(adminName != null || clientName != null) {%>
			  <form class="d-flex" action="/logout" method="post">
		         <button class="btn text-primary" type="submit"><i class="fa-solid fa-right-from-bracket"></i></button>
			  </form>
		  <%} %>
		  <% if(clientName != null){ %>
		  	<a class="btn text-primary" href="/cart"><i class="fa-solid fa-cart-shopping"></i></a>
		  <%} %>
	    </div>
	  </div>
	</nav>

		
		
		
			
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
</body>
</html>