<%@page import="model.Product"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Product product  = (Product) request.getAttribute("product") ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class='container py-5'>
	
		<div class="row">
			<div class="col-md-6">
			 	<img alt="" src=<%=product.getImageUrl() %> class="w-100 rounded">		
			</div>
			<div class="col-md-6 py-5">
				<h3 class="mt-2 text-capitalize"><%=product.getNom()%></h3>
				<p><%=product.getDescription()%></p>

				<strong class="text-warning"><%= product.getPrix()%> MAD</strong>
				<form action="/addToCart" method="post" class="d-flex align-items-center mt-5">
					<input value="<%=product.getId()%>" hidden="true" name="id" width="30px">
					<div class="d-flex align-items-center justify-content-center">
						<button class="btn btn-dark rounded-0" onclick="decrement(event)">-</button>
						<input class="form-control rounded-0" style="width: 40px"  value="0" id="qte" name="qte" min="0">
						<button class="btn btn-dark rounded-0" onclick="increment(event)">+</button>
					</div>
					<button class="ms-3 btn btn-dark rounded-0"><i class="fa-solid fa-cart-shopping me-3"></i>Add to cart</button>
				</form>
			</div>
		</div>
		
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<script type="text/javascript">
		const input = document.getElementById("qte") 
		
		
		function increment(event) { 
			event.preventDefault() 
			
			if(input.value < 10 ) {
				input.value = Number.parseInt(input.value) + 1 
 			}
		}
		
		function decrement(event) { 
			event.preventDefault() 
			if(input.value > 0 ) {
				input.value = Number.parseInt(input.value) - 1 
 			}
		}
	
	
	</script>
</body>
</html>