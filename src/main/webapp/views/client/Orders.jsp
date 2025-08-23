
<%@page import="model.Order"%>
<%@page import="java.util.List"%>
<%@page import="model.CartItem"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Order> orders = (List<Order>)request.getAttribute("orders")    ;  
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
		<h1 class='ms-3'> My Orders List</h1>
		
		
		<%if(orders != null && !orders.isEmpty()) {%>
				<table class="table mt-5 rounded">
					<thead>
						<tr> 
							<th class="py-3">#ID</th>
							<th class="py-3">date</th>
							<th class="py-3">total Price</th>
							<th class="py-3">shipping Address</th>
							<th class="py-3">shipping Status</th>
						</tr>
					</thead>
					<tbody>
						<%if( orders != null && orders.size() > 0 ) {%>
							<%for( Order order : orders ) {%>
								<tr>
									<td class="py-3"><%=order.getId() %></td>
									<td class="py-3"><%=order.getDate().toLocaleString() %></td>
									<td class="py-3"><%=order.getTotalPrice() %> MAD</td>
									<td class="py-3"><%=order.getShippingAddress()%></td>
									<td class="py-3">
										<div class="badge rounded-pill <%=order.getShippingStatus().name().equalsIgnoreCase("CANCELLED") ? "text-bg-danger" : order.getShippingStatus().name().equalsIgnoreCase("SHIPPED") ? "text-bg-success" : "text-bg-warning" %>"> 
											<%=order.getShippingStatus() %> 
										</div>
										
									</td>
									
								</tr>
							<%} %>
						<%}else {%>
							<tr>
								<td colspan="6">no order found</td>
							</tr>
					
						<%}%>
					</tbody>
				</table>
		<%}else {  %>
			<p class='text-center'>No orders exists</p>
		<%} %>
	</div>
</body>
</html>