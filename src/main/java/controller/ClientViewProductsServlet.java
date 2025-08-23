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

@WebServlet("/products")
public class ClientViewProductsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DaoProduct daoProduit ; 
	
	
	@Override
	public void init() throws ServletException {
		this.daoProduit = new DaoProduct() ; 
 	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("search") ;  
		List<Product> produits = null ; 
		if(key != null && !key.trim().isEmpty() ) { 
			produits = daoProduit.getAllContains(key) ; 

		}else { 
			 produits = daoProduit.getAll() ; 
		}
		req.setAttribute("search", key);
		req.setAttribute("products", produits != null ? produits  : new ArrayList<Product>()) ; 
		req.getRequestDispatcher("/views/client/Products.jsp").forward(req, resp) ; 
	}
}
