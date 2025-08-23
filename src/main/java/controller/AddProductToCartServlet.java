package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import utils.SessionUtils;
import utils.Utils;
import java.io.IOException;
import dao.DaoProduct;

@WebServlet("/addToCart")
public class AddProductToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  DaoProduct daoProduit ; 
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		this.daoProduit  = new DaoProduct() ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id") ; 
		String qte = request.getParameter("qte") ; 
		
		if(Utils.verifyField(id) && Utils.verifyField(qte) && Integer.parseInt(qte) != 0 ) { 
			
			Cart panier = SessionUtils.getSessionAttribute(request, "cart") != null ? (Cart)SessionUtils.getSessionAttribute(request, "cart") : new Cart() ; 
			String message = null ; 
			try { 
				panier.ajouterProduit(new CartItem(this.daoProduit.getById(Integer.parseInt(id) ).get() , Integer.parseInt(qte) ));
				
				boolean created = SessionUtils.setSessionAttribute(request , "cart", panier) ; 
				message = created ? "the product added succeddfuly to the cart" : "cannot add the product to card try again plaise" ; 
			}catch (NumberFormatException numberFormatException  ) {
				System.out.println(numberFormatException.getMessage());
				message = "cannot add the product to card try again plaise" ; 
			}
		}
		
		response.sendRedirect("/products");	
	}

}
