package controller;

import java.io.IOException;

import dao.DaoAdmin;
import dao.DaoClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet("/admin/deleteClient")
public class DeleteClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoClient daoClient ; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoClient = new DaoClient(); 
 	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idField = req.getParameter("id") ; 
		
		if(Utils.verifyField(idField)) { 
			int id  = Integer.parseInt(idField)  ; 
			
			this.daoClient.deleteById(id); 
			
			
			req.setAttribute("message", "the client deleted succesfully") ; 
		}else { 
			req.setAttribute("message", "cannot delete the client try later") ; 
		}
		
		req.setAttribute("clients", daoClient.getAll()) ; 
		req.getRequestDispatcher("/views/admin/Clients.jsp").forward(req, resp) ; 
		
	}
	
}
