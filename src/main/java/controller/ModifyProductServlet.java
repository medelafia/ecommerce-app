package controller;


import java.io.IOException;

import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import utils.Utils;



@WebServlet("/admin/modifyProduct")
public class ModifyProductServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private DaoProduct daoProduit ; 
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoProduit = new DaoProduct() ; 
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id") ; 
		if(Utils.verifyField(idString ) ) {  
			try { 
				int id = Integer.parseInt(idString) ; 
				Product produit  = this.daoProduit.getById(id).orElse(null)  ; 
				if(produit == null ) { 
					resp.sendRedirect("/admin/products"); 
					return ; 
				}
				req.setAttribute("produit", produit);
				req.getRequestDispatcher("/views/admin/ModifyProduct.jsp").forward(req, resp);
				
			}catch (NumberFormatException exception) {
				resp.sendRedirect("/admin/products") ; 
			
			}

		}

	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id") ; 
		String nom = req.getParameter("nom") ; 
		String description = req.getParameter("description")  ; 
		String prix = req.getParameter("prix")  ; 
		String qte = req.getParameter("qte") ;  
		
		if(Utils.verifyField(id) && Utils.verifyField(nom) && Utils.verifyField(description) && Utils.verifyField(prix) && Utils.verifyField(qte))  { 
			try { 
				
				daoProduit.update(new Product(Integer.parseInt(id) ,nom , description , Double.parseDouble(prix) , Integer.parseInt(qte) , null ));
				req.setAttribute("message", "product updated succesfully") ;
				
			}catch (NumberFormatException exception ) {
				
				req.setAttribute("message", "invalid field format") ;
			}
		}else { 
			req.setAttribute("message", "all fields required") ;
		}
		resp.sendRedirect("/admin/products");
	} 

}
