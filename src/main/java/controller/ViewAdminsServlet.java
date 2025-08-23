package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoAdmin;
import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Product;

@WebServlet("/admin/admins")
public class ViewAdminsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DaoAdmin daoCommercial ; 
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoCommercial = new DaoAdmin() ; 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Admin> commercials = this.daoCommercial.getAll() ;  
		
 		req.setAttribute("admins", commercials !=  null ? commercials : new ArrayList<>()); 
		req.getRequestDispatcher("/views/admin/Admins.jsp").forward(req, resp) ; 
	}
}
