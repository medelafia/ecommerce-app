package controller;

import java.io.IOException;

import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet("/admin/deleteProduct")
public class DeleteProductSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoProduct daoProduit ; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoProduit = new DaoProduct() ;
 	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idField = req.getParameter("id") ; 
		
		if(Utils.verifyField(idField)) { 
			int id  = Integer.parseInt(idField)  ; 
			
			this.daoProduit.deleteById(id) ;
			
			
			req.setAttribute("message", "the product deleted succesfully") ; 
		}else { 
			req.setAttribute("message", "cannot delete the product try again") ; 
		}

		resp.sendRedirect("/admin/products"); 
		
	}
	
}
