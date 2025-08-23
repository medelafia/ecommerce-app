package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends  HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession httpSession  = req.getSession() ; 
		
		if(httpSession != null ) {  
			
			if(httpSession.getAttribute("client")  != null ) { 
				httpSession.removeAttribute("client");
			}
			if(httpSession.getAttribute("commercial")  != null) {
				httpSession.removeAttribute("commercial") ; 
			}
		}
		
		resp.sendRedirect("/index");; 
	}
}
