<%@page import="enums.ShippingStatus"%>
<%@page import="model.Order"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<% List<Order> orders = (List<Order>)request.getAttribute("orders"); 
	String message = (String)request.getAttribute("message"); 
	List<String> sippingStatusValues = (List<String>)request.getAttribute("shippingStatusValues"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
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
			<div class="container px-5 h-100">
				<jsp:include page="../alert.jsp" />
				<div class='d-flex align-items-center justify-content-between' > 
					<h1 class='mt-5'># Orders</h1> 
					<form action="/admin/download" method="post">
						<input hidden="true" value="orders" name="target"> 
						<button type="submit" class="btn btn-outline-dark"><i class="fa-solid fa-download me-2"></i>download</button>
					</form>
				</div> 
				<table class="table mt-5 rounded">
					<thead>
						<tr> 
							<th>#ID</th>
							<th>date</th>
							<th>total Price</th>
							<th>shipping Address</th>
							<th>shipping Status</th>
							<th>client name</th>
						</tr>
					</thead>
					<tbody>
						<%if( orders != null && orders.size() > 0 ) {%>
							<%for( Order order : orders ) {%>
								<tr>
									<td><%=order.getId() %></td>
									<td><%=order.getDate().toLocaleString() %></td>
									<td><%=order.getTotalPrice() %></td>
									<td><%=order.getShippingAddress()%></td>
									<td>
									
										<div class="badge rounded-pill <%=order.getShippingStatus().name().equalsIgnoreCase("CANCELLED") ? "text-bg-danger" : order.getShippingStatus().name().equalsIgnoreCase("SHIPPED") ? "text-bg-success" : "text-bg-warning" %>"> 
											<%=order.getShippingStatus() %> 
										</div>

										
										<button onclick="" class="btn" data-order-id="<%=order.getId()%>"  data-bs-target="#changeOrderStatusModal" data-bs-toggle="modal" >
											<i class="fa-solid fa-gear"></i>
										</button>
									</td>
									<td><%=order.getClient().getNom()%></td>
									
								</tr>
							<%} %>
						<%}else {%>
							<tr>
								<td colspan="6">no orders found</td>
							</tr>
					
						<%}%>
					</tbody>
				</table>
				</div>
			</div>
			<div class="modal fade" id="changeOrderStatusModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			       
			      <div class="modal-body p-5">
			      	<h4>Change shipping status</h4>
			      	<form action="/admin/changeOrderStatus" method="post" class="py-4">
			      		<input hidden id="order_id_input" name="id">
			      		<select name="new_status" class="form-select mb-3"> 
			      			<%for(String shippingStatus : sippingStatusValues){%> 
			      				<option value=<%=shippingStatus%>><%=shippingStatus%></option>
			      			<% }%>

			      		</select>
			      		
			      		<button class="btn btn-primary">Save</button>
			      	</form>
			      </div>
			      
			    </div>
			  </div>
			</div>
		</div>
	<script type="text/javascript">
		const order_id_input = document.getElementById("order_id_input") ; 
		const modal = document.getElementById("changeOrderStatusModal") ; 
		
		modal.addEventListener('show.bs.modal', function (event) {
			const target = event.relatedTarget 
			order_id_input.value= target.getAttribute("data-order-id") 
			
		}) 
		
	
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>