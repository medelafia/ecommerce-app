package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import utils.SessionUtils;
import utils.Utils;

import java.io.IOException;

import com.mysql.cj.Session;


@WebServlet("/deleteFromCart")
public class DeleteProductFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DeleteProductFromCartServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartItemId = request.getParameter("id") ; 
		
		
		if(!Utils.verifyField(cartItemId)) { 
			response.sendRedirect("/cart");
			return ; 
		}
		
		try { 
			int id = Integer.parseInt(cartItemId ) ; 
			Cart cart = (Cart)SessionUtils.getSessionAttribute(request, "cart") ; 
			cart.deleteFromCard(id) ; 
			boolean created = SessionUtils.setSessionAttribute(request , "cart", cart) ; 
			request.setAttribute("message", created ? "the product added succeddfuly to the cart" : "cannot add the product to card try again plaise") ; 
			request.setAttribute("cart", cart); 
		}catch (NumberFormatException e) {
			request.setAttribute("message", "cannot delete the product please try again") ; 
		}
		
		request.getRequestDispatcher("/views/client/Cart.jsp").forward(request, response) ; 
	}

}
