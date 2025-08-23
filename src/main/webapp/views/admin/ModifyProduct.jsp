<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Product produit = (Product)request.getAttribute("produit") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Produit</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
	<div class='row h-100' style="background-color:#d0f4de">
		<div class="col-md-2 p-5">
			<jsp:include page="SideBar.jsp" /> 
		</div>
		<div class="col-md-10 rounded-5 bg-white h-100"> 
			<jsp:include page="navbar.jsp" />
			<div class="container d-flex align-items-center justify-content-center py-3">
				<form action="/admin/modifyProduct" method="post" class="p-5 rounded bg-white w-50" style="border:1px solid #D3D3D3; border-top :10px solid #d0f4de;">
					<h2 class="mt-2 mb-2">Modify Product</h2>
					<div class="form-group">
						<input type="text" id="id" value="<%=produit.getId()%>" name="id" required="required" hidden="true" class="form-control"/>
					</div>
					<div class="form-group my-2">
						<label for="nom">Nom </label>
						<input type="text" id="nom" value="<%=produit.getNom()%>" name="nom" required="required" placeholder="entrer le nom de produit" class="form-control"/>
					</div>
					<div class="form-group my-2">
						<label for="description">Description </label>
						<textarea rows="5" id="description"   name="description" class="form-control"><%=produit.getDescription()%></textarea>
					</div>
					<div class="form-group my-2">
						<label for="prix">Prix</label>
						<input type="text" id="prix" name="prix" value="<%=produit.getPrix()  %>" class="form-control" placeholder="entrer le prix de produit"/>
					</div>
					<div class="form-group my-2">
						<label for="nom">Qte </label>
						<input type="number" id="qte" name="qte" value="<%=produit.getQuantite_stock() %>" class="form-control" placeholder="entrer la quantite de produit"/>
					</div>
					<button class="btn w-100 btn-dark mt-3">modifier produit</button>
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
</body>
</html>