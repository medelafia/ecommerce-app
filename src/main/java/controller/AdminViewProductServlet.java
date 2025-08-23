package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet("/admin/products")
public class AdminViewProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DaoProduct daoProduit  ; 
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoProduit = new DaoProduct() ; 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try { 
				
			int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page")) ; 
			int size = req.getParameter("size") == null ? 5 : Integer.parseInt(req.getParameter("size")) ; 
			
			
			List<Product> produits = daoProduit.getPage(page, size) ; 
			int nbPages = (int) Math.ceil( daoProduit.getCount() / size )  ;
			
			
			req.setAttribute("size", size ) ;
			req.setAttribute("products", produits !=  null ? produits : new ArrayList<>()); 
			req.setAttribute("pages", nbPages);
			req.getRequestDispatcher("/views/admin/Products.jsp").forward(req, resp) ; 
	
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}
}
