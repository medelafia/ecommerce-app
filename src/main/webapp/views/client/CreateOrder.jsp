<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container p-5 d-flex align-items-center justify-content-center w-100"> 
	
			<form action="/createOrder" method="post" class="p-5 rounded shadow bg-white w-75">
				<h2 class="mt-3 mb-3">Order Now !</h2>
				<div class="form-group my-2">
					<label for="nom">shipping address </label>
					<input type="text" id="nom" name="shipping_address" required="required" placeholder="enter the shipping  address" class="form-control"/>
				</div>
	
				<button class="btn w-100 btn-dark mt-3">validate</button>
			</form>
		</div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>