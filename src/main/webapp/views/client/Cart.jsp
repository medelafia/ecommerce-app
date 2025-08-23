<%@page import="model.CartItem"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Cart cart = (Cart)request.getAttribute("cart")    ;  
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
	<jsp:include page="../header.jsp" />
	<div class='container mt-5'>
		<jsp:include page="../alert.jsp" />
		<h1 class='ms-3'>My Shopping Cart</h1>
		
		
		<%if(cart != null && !cart.getPanierItems().isEmpty()) {%>
			<div class='row'>
				<%for(CartItem cartItem : cart.getPanierItems()) {%>
					<div class='container'>
						<div class='rounded border p-3 my-2 w-100'>
							<div class='d-flex align-items-center justify-content-between w-100' >
								<h3><%=cartItem.getProduit().getNom()%></h3>
								<p class="text-warning"><%=cartItem.getQte()%> Units</p>
								<p class="text-warning"><%=cartItem.getProduit().getPrix()%>  MAD</p>
								<form method="post" action='/deleteFromCart?id=<%=cartItem.getProduit().getId() %>'> 
									<button class='btn text-danger'><i class="fa-solid fa-trash"></i></button>
								</form>
							</div>
						</div>
					</div>	
				<%} %>
				<div class='container'>
					<div class='rounded border-top p-3 my-2 w-100'>
						<div class='d-flex align-items-center justify-content-between w-100' >
							<h5 class='text-caplitalize'>Total :</h5>
							<p><%=cart.getTotal()%> MAD</p> 
							<a href='/createOrder' class='btn btn-dark'>validate now</a>
						</div>
					</div>
				</div>
			</div>
		<%}else {  %>
			<p class='text-center'>le panier est vide</p>
		<%} %>
	</div>
</body>
</html>