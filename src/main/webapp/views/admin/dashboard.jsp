
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String[] mostSelledProductsNames =  (String[])request.getAttribute("mostSelledProductsNames") ; 
        Double[] mostSelledProductsIncomes = (Double[])request.getAttribute("mostSelledProductsIncomes")  ; 
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />


<style type="text/css">

	.box-icon {
		position: absolute; 
		right : 10px ; 
		top : 10px ; 
		color: #ffe5d9;
		font-size: 100px ; 
	}	
	.box-color { 
		background-color: #fec89a ; 
	}
</style>
</head>
<body style="height: 100vh">
	<div class='row h-100' style="background-color:#d0f4de">
		<div class="col-md-2 p-5">
			<jsp:include page="SideBar.jsp" /> 
		</div>
		<div class="col-md-10 rounded-5 border bg-white">
			<jsp:include page="navbar.jsp" />
			<div class="container px-5 ">
				<h1 class="my-3">Dashboard</h1>
				<div class="row">
					<div class="col-md-4 container">
						<div class="box-color p-5 rounded position-relative overflow-hidden d-flex flex-column align-items-start">
							<strong> <%=request.getAttribute("nbProducts") %> Products</strong>
							<i class="fa-solid fa-cubes-stacked box-icon"></i>
							<a class="btn text-decoration-underline mt-3 text-white" href="/admin/products">show more <i class="fa-solid fa-arrow-right"></i></a>
						</div>
					</div>
					<div class="col-md-4 container ">
						<div class="box-color p-5 rounded position-relative overflow-hidden d-flex flex-column align-items-start">
							<strong><%=request.getAttribute("nbClients") %> Clients</strong>
							<i class="fa-solid fa-user box-icon"></i>
							<a class="btn text-decoration-underline mt-3 text-white" href="/admin/admins">show more <i class="fa-solid fa-arrow-right"></i></a>
						</div>
					</div>
					<div class="col-md-4 container">
						<div class="box-color p-5 rounded position-relative overflow-hidden d-flex flex-column align-items-start">
							<strong><%=request.getAttribute("nbOrders") %> Order</strong>
							<i class="fa-solid fa-cart-shopping box-icon"></i>
							<a class="btn text-decoration-underline mt-3 text-white" href="/admin/orders">show more <i class="fa-solid fa-arrow-right"></i></a>
						</div>
					</div>
					
					<div class="col-md-6 col-sm-12 container my-5 d-flex flex-column align-items-center">
						<div class="text-uppercase">most rentable products</div>
						<canvas id="canva-1" class="w-100 mt-2" height="250px"></canvas>
					</div>
					<div class="col-md-6 col-sm-12 container my-5  d-flex flex-column align-items-center">
						<div class="text-uppercase">most profitable products</div>
						<canvas id="canva-2" class="w-100 mt-2" height="250px"></canvas>
					</div>
					
				</div>
			</div>
			
		</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script type="text/javascript"> 
		const xValues = [50,60,70,80,90,100,110,120,130,140,150];
		const yValues = [7,8,8,9,9,9,10,11,14,14,15];
		const chart1 = new Chart("canva-1" ,  { 
			  type: "line",
			  data: {
				   labels: xValues,
				   datasets: [{
				      backgroundColor:"#d0f4de",
				      borderColor: "rgba(0,0,255,0.1)",
				      data: yValues
				    }]
				},
		})
		
		const xValues1 = [ 
			<%for(int i = 0 ; i < mostSelledProductsNames.length ; i++){%>
				
				"<%=mostSelledProductsNames[i]%>" <%= (i == mostSelledProductsNames.length - 1) ? "" : "," %>
			<%}%>
		]
		const yValues1 = [ 
			<%for(int i = 0 ; i < mostSelledProductsIncomes.length ; i++){%>
				<%=mostSelledProductsIncomes[i]%> <%= (i == mostSelledProductsIncomes.length - 1) ? "" : "," %>
			<%}%>
		];
		console.log(yValues1)
		
		const barColors = [
			<%for(int i=0 ; i < mostSelledProductsNames.length ; i++ ) { %>
				"<%="#e8e8e" + (i+5) %>" <%= (i == mostSelledProductsIncomes.length - 1) ? "" : "," %>
			<%}%>	
			
			];

		const chart2 = new Chart("canva-2", {
		  type: "bar",
		  data: {
		    labels: xValues1,
		    datasets: [{
		      backgroundColor: barColors,
		      data: yValues1
		    }]
		  }
		});
	</script>
</body>
</html>