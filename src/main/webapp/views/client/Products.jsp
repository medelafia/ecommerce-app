<%@page import="model.Product"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Product> products  = (List<Product>) request.getAttribute("products") ; 
	String search = request.getAttribute("search") == null ? "" : (String)request.getAttribute("search") ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogue</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class='container'>
		<h1 class='mt-5'># Produits</h1> 
		<div class='row mt-4'> 
		<%if(products  != null && products.size() > 0 ) {%>
			<% for (Product product : products ) {%>
				<div class='col-sm-12 col-md-6 col-lg-4 my-3'>
					<div class='container  p-3 rounded'>
						<img alt="" src=<%=product.getImageUrl() %> class="w-100 rounded" height="200px">
						<h3 class="mt-2 text-capitalize"><%=product.getNom()%></h3>
						<p><%=product.getDescription()%></p>
						<div class='d-flex align-items-center justify-content-between w-100 ' >
							
							<strong class="text-warning ="><%= product.getPrix()%> MAD</strong>
							<a href="/product?id=<%=product.getId()%>" class="btn btn-dark">View Product</a>
							
						</div>
					</div>
				</div>
			<% }%>	
		<%}else {%>
			<div class='col-sm-12 container'>
				<h5>il ya aucune produit</h5>
			</div>
		<% }%>	
		</div>
		</div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>