package controller;

import java.io.IOException;
import java.util.List;

import dao.DaoAdmin;
import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;

@WebServlet("/index")
public class IndexServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private DaoProduct daoProduit ; 
	private DaoAdmin daoAdmin ; 
	
	
	public IndexServlet()  { 
		this.daoProduit = new DaoProduct() ; 
		this.daoAdmin = new DaoAdmin() ; 
	}
	
	public void createFirstAdmin() { 
		if(!this.daoAdmin.getByEmailAndPassword(System.getenv("ADMIN_EMAIL"), System.getenv("ADMIN_PASSWORD")).isPresent()) {
			this.daoAdmin.save(
					new Admin.Builder()
							.setEmail(System.getenv("ADMIN_EMAIL"))
							.setNom(System.getenv("ADMIN_NOM"))
							.setPassword(System.getenv("ADMIN_PASSWORD"))
							.build()
			);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.createFirstAdmin();
		
		Long productsCount = this.daoProduit.getCount() ; 
		
		req.setAttribute("products", productsCount > 0 ? this.daoProduit.getAll().subList(0 , (int)Math.min(3, productsCount)) : null  ) ;
	
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp) ; 
		
	}
}
