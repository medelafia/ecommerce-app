<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Product> products = (List<Product>)request.getAttribute("products"); 

	int nbPages = (int)request.getAttribute("pages") ; 
	int size = (int)request.getAttribute("size") ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produits</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body style="height: 100vh">
	<div class='row h-100' style="background-color:#d0f4de">
		<div class="col-md-2 p-5">
			<jsp:include page="SideBar.jsp" /> 
		</div>
		<div class="col-md-10 rounded-5 border bg-white h-100"> 
			<jsp:include page="navbar.jsp" />
			<div class="container px-5">
				
				<div class='d-flex align-items-center justify-content-between' > 
					<h1 class='mt-5'>#Products</h1> 
					<div class='d-flex align-items-center justify-content-between'>
						<form action="/admin/download" method="post">
							<input hidden="true" value="products" name="target"> 
							<button type="submit" class="btn btn-outline-dark"><i class="fa-solid fa-download me-2"></i>download</button>
						</form>
						<button data-bs-target="#importFileModal" data-bs-toggle="modal" class='ms-2 btn btn-dark' ><i class="fa-solid fa-file-import me-2"></i>import</button>
						<button data-bs-target="#addProductModal" data-bs-toggle="modal" class='ms-2 btn btn-outline-dark' ><i class="fa-solid fa-plus me-2"></i>Add Product</button>
					</div>
				</div>
				<table class="table mt-5 rounded">
					<thead>
						<tr> 
							<th>#ID</th>
							<th>nom</th>
							<th>description</th>
							<th>prix</th>
							<th>qte</th>
							<th>action</th>
						</tr>
					</thead>
					<tbody>
						<%if( products != null && products.size() > 0 ) {%>
							<%for( Product product : products ) {%>
								<tr>
									<td><%=product.getId() %></td>
									<td><%=product.getNom() %></td>
									<td><%=product.getDescription() %></td>
									<td><%=product.getPrix()%></td>
									<td><%=product.getQuantite_stock() %></td>
									<td class="d-flex align-items-center">
										<a href="/admin/modifyProduct?id=<%=product.getId()%>" class="btn me-1 text-success"><i class="fa-solid fa-pen-to-square"></i></a>
										<form action="/admin/deleteProduct" class="ms-1"  method="post">
											<input name="id" hidden="true" value="<%=product.getId()%>">
											<button class="btn text-danger"><i class="fa-solid fa-trash"></i></button>
										</form>
										<a></a>
									</td>
								</tr>
							<%} %>
						<%}else {%>
							<tr>
								<td colspan="6">il ya aucune produits</td>
							</tr>
					
						<%}%>
					</tbody>
				</table>
				
				
				<div class="d-flex align-items-center justify-content-center my-4"> 
					<% for(int i = 1 ; i <= nbPages  ; i++ ){ %> 
						<a class="btn btn-outline-dark mx-1" href="/admin/products?page=<%=i%>&size=<%=size%>">
								<%=i%>
						</a>
					<% } %> 
				</div>
				</div>
			</div>
			<div class="modal fade" id="addProductModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered modal-xl">
			    <div class="modal-content">
	
			      <form action="/admin/addProduct" method="post" class="p-5 rounded shadow bg-white" enctype="multipart/form-data">
					<h2 class="mt-3 mb-3">Ajouter produit</h2>
					<input type="file" accept="image/*" name="image"/>
					
					<div class="form-group my-1">
						<label for="nom">Nom </label>
						<input type="text" id="nom" name="nom" required="required" placeholder="entrer le nom de produit" class="form-control"/>
					</div>
					<div class="form-group my-1">
						<label for="description">Description </label>
						<textarea rows="10" id="description" name="description" class="form-control"></textarea>
					</div>
					<div class="form-group my-1">
						<label for="prix">Prix</label>
						<input type="text" id="prix" name="prix" class="form-control" placeholder="entrer le prix de produit"/>
					</div>
					<div class="form-group my-1">
						<label for="nom">Qte </label>
						<input type="number" id="qte" name="qte" class="form-control" placeholder="entrer la quantite de produit"/>
					</div>
					<div class="d-flex align-items-center justify-content-start mt-3">
						<button class="btn btn-outline-dark me-2 " data-bs-dismiss="modal">Cancel</button>
						<button class="btn btn-dark ms-2">Add product</button>
					</div>
					
				</form>
			      
			    </div>
			  </div>
			</div>
			<div class="modal fade" id="importFileModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      
			      <div class="modal-body">
			      	<h4>upload file</h4>
			      	<form action="/admin/importFromServlet" enctype="multipart/form-data" method="post">
			      		<input type="file" accept=".csv" name="file">
			      		
			      		<button class="btn btn-primary">Upload now</button>
			      	</form>
			      </div>
			      
			    </div>
			  </div>
			</div>

		</div>
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>