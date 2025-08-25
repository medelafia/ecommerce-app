<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>

	*{ 
		margin: 0 ;
	}
	body { 
		height: 100vh;
	}

	@media (max-width : 600px ) {
		#image-part {
			display: none;
		}
	}
	
</style>

</head>

<body >

	<div class="row h-100">
		<div class="col-lg-6 h-100" id="image-part">
			<img alt="" src="../static/490-4908098_ecommerce-website-design-company-in-nashik-e-commerce.png" height="100%" width="100%">
		</div>
		<div class='col-lg-6 bg-light d-flex align-items-center justify-content-center h-100 col-sm-12'>
			<form action="/login?type=<%=request.getParameter("type")%>" method="post" class='p-5 w-75'>
				<%if(request.getAttribute("message") != null ){%>
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
					  <strong><%=request.getAttribute("message") %></strong> 
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				<%}%>
				<h1 class="mb-3">Connexion</h1>
				<div class="form-group my-2">	
					<label for='login'>login</label>
					<input name='login' id='login' class='form-control' >
				</div>
				<div class="form-group my-2">
					<label for='motDePasse'>Mod de passe</label>
					<input type='password' name='motDePasse' id='motDePasse' class='form-control'>
				</div> 
				<button class='btn btn-dark my-2'>connexion</button>
				
				
				<%if(request.getAttribute("type") != null && request.getAttribute("type").toString().equals("client")) {%>
					<div>vous avez pas un compte ? <a href="/register">inscription</a> </div>
				<%}%>
				
			</form>
		</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js" integrity="sha512-ykZ1QQr0Jy/4ZkvKuqWn4iF3lqPZyij9iRv6sGqLRdTPkY69YX6+7wvVGmsdBbiIfN/8OdsI7HABjvEok6ZopQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
</body>
</html>