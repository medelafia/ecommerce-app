package controller;

import java.io.IOException;

import dao.DaoAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet("/admin/deleteAdmin")
public class DeleteAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoAdmin daoCommercial  ; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoCommercial = new DaoAdmin() ; 
 	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idField = req.getParameter("id") ; 
		
		if(Utils.verifyField(idField)) { 
			int id  = Integer.parseInt(idField)  ; 
			
			this.daoCommercial.deleteById(id);
			
			
			req.setAttribute("message", "the product deleted succesfully") ; 
		}else { 
			req.setAttribute("message", "cannot delete the product try again") ; 
		}
		
		req.setAttribute("commercials", daoCommercial.getAll()) ; 
		req.getRequestDispatcher("/views/admin/Admins.jsp").forward(req, resp) ; 
		
	}
	
}
