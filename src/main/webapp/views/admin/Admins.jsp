
<%@page import="model.Admin"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
List<Admin> admins = (List<Admin>)request.getAttribute("admins"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admins</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body style="height: 100vh">
	<div class='row h-100' style="background-color:#d0f4de">
		<div class="col-md-2 p-5">
			<jsp:include page="SideBar.jsp" /> 
		</div>
		<div class="col-md-10 rounded-5 border bg-white"> 
			<jsp:include page="navbar.jsp" />
			<div class="container px-5">
				<jsp:include page="../alert.jsp" />
				<div class='d-flex align-items-center justify-content-between' > 
					<h1 class='mt-5'># Admins</h1> 
					<div class='d-flex align-items-center justify-content-between'>
						<button data-bs-target="#exampleModalToggle" data-bs-toggle="modal" class='ms-2 btn btn-outline-dark' ><i class="fa-solid fa-plus me-2"></i>Add admin</button>
					</div>
				</div>
				<table class="table mt-5 rounded">
					<thead>
						<tr> 
							<th>#ID</th>
							<th>nom</th>
							<th>email</th>
							<th>mot de passe</th>
							<th>action</th>
						</tr>
					</thead>
					<tbody>
						<%if( admins != null && admins.size() > 0 ) {%>
							<%for( Admin admin : admins ) {%>
								<tr>
									<td><%=admin.getId() %></td>
									<td><%=admin.getNom() %></td>
									<td><%=admin.getEmail() %></td>
									<td><%=admin.getMot_de_passe()%></td>
									<td class="d-flex align-items-center">
										<form action="/admin/deleteAdmin" class="ms-1"  method="post">
											<input name="id" hidden="true" value="<%=admin.getId()%>">
											<button class="btn text-danger"><i class="fa-solid fa-trash"></i></button>
										</form>
										<a></a>
									</td>
								</tr>
							<%} %>
						<%}else {%>
							<tr>
								<td colspan="6">il ya aucune commercials</td>
							</tr>
					
						<%}%>
					</tbody>
				</table>
				</div>
			</div>
			<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
	
			      	<form action="/admin/addAdmin" method="post" class="p-5 rounded shadow bg-white">
						<h2 class="mt-3 mb-3">Add Admin</h2>
						<div class="form-group my-2">
							<label for="nom">Nom </label>
							<input type="text" id="nom" name="nom" required="required" placeholder="entrer le nom de commercial" class="form-control"/>
						</div>
			
						<div class="form-group my-2">
							<label for="prix">email</label>
							<input type="email" id="prix" name="email" class="form-control" placeholder="entrer l'email de commercial"/>
						</div>
						<div class="d-flex align-items-center justify-content-start mt-5">
							<button class="btn btn-outline-dark me-2 " data-bs-dismiss="modal">Cancel</button>
							<button class="btn btn-dark ms-2">Add admin</button>
						</div>				
					</form>
			      
			    </div>
			  </div>
			</div>
		</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>