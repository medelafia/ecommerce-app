
<%@page import="model.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
List<Client> clients = (List<Client>) request.getAttribute("clients"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clients</title>
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
				<div class='d-flex align-items-center justify-content-start' > 
					<h1 class='mt-5'># Clients</h1> 
				</div>
				<table class="table mt-5 rounded">
					<thead>
						<tr> 
							<th>#ID</th>
							<th>nom</th>
							<th>email</th>
							<th>account verified</th>
							<th>action</th>
						</tr>
					</thead>
					<tbody>
						<%if( clients != null && clients.size() > 0 ) {%>
							<%for( Client client : clients ) {%>
								<tr>
									<td><%=client.getId() %></td>
									<td><%=client.getNom() %></td>
									<td><%=client.getEmail() %></td>
									<td>
										<div class="badge rounded-pill <%=client.isAccountVerified() ? "text-bg-success" :  "text-bg-danger" %>"> 
											<%=client.isAccountVerified() ? "Verified" :  "Not verified" %> 
										</div>
									</td>
									<td class="d-flex align-items-center">
										<form action="/admin/deleteClient" class="ms-1"  method="post">
											<input name="id" hidden="true" value="<%=client.getId()%>">
											<button class="btn text-danger"><i class="fa-solid fa-trash"></i></button>
										</form>
										<a></a>
									</td>
								</tr>
							<%} %>
						<%}else {%>
							<tr>
								<td colspan="6">there is no client</td>
							</tr>
					
						<%}%>
					</tbody>
				</table>
				</div>
			</div>
			
		</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>