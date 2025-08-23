<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
List<Product> products = (List<Product>)request.getAttribute("products") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-commerce</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container bg-light rounded">
		<div class="my-5 py-5 row" >
			<div class="col-md-6 px-5" > 
				<p style="font-size: 60px; " class="text-primary" >
					Bienvenue a Espace E-Commerce 
				</p>
				<p>
					Discover the best products at unbeatable prices.
					From everyday essentials to unique finds, we bring quality and style right to your doorstep.
					Shop with ease, enjoy secure payments, and fast delivery.
				</p>
				<a href="/products" class="btn btn-outline-primary mt-3">Go shopping<i class="fa-solid fa-arrow-right ms-3"></i></a>
			</div>
			<div class="col-md-6" > 
			  	<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  					<div class="carousel-inner">
						<div class="carousel-item active">
				      		<img src="/static/2-split-holiday-gift-guide-2023-1-1.webp" class="d-block"  width="600px" height="400px" alt="...">
				    	</div>
				    	<div class="carousel-item">
				      		<img src="/static/pl-black-friday-1-1.avif" class="d-block" width="600px" height="400px" alt="...">
				   		 </div>
  					</div>
				</div>
			</div>
		
		</div>
	</div>
	
	<div style="width:100% ; height : 500px ; " class="container p-5">
		<div class="bg-dark row h-100 rounded-5">
			<div class="col-md-6 p-5">
				<div class="container p-5" >
				 	<h1 class="text-white">Find your best products</h1>
				 	<input class="form-control mt-5 rounded-pill p-3" placeholder="serach for product">
				 </div>
			</div>
			<div class="col-md-6 d-flex align-items-center justify-content-center">
				<img alt="" src="/static/ff1ad43f10afcf927ed3024c5fe3ebd5.jpg" width="400px" height="300px">
			</div>
		</div>
	</div>
	
	
	<div style="width:100% ; height : 600px ; " class="bg-light p-5 d-flex align-items-center">
		<div class="container">
			<h1>Explore more than 100 products!</h1>
			<div class="row h-100 rounded-5 py-3">
			
				<% if(products != null && products.size()  > 0 ){%>
					<c:forEach items="${products}"  var="product"> 
						<div class='col-sm-12 col-md-6 col-lg-4 container'>
							<div class='rounded p-3'>
							
								<img alt="" src="${product.getImageUrl()}"  class="w-100 rounded" height="250px">
								<h3 class="mt-2 text-capitalize">${product.getNom()}</h3>
								<p>${product.getDescription()}</p>
								<div class='d-flex align-items-center justify-content-between w-100' >
									
									<strong class="text-warning =">${product.getPrix()} MAD</strong>
									<form action="/addToCart" method="post">
										<input value="${product.getId()}" hidden="true" name="id">
										<button class="btn text-dark"><i class="fa-solid fa-cart-shopping"></i></button>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				<%} else {%>  
					<div class="my-5">
						No Products exists yet
					</div>
				<%}%> 
			</div>
		</div>
	</div>
	
	
	
	<jsp:include page="footer.jsp" />
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
</body>
</html>