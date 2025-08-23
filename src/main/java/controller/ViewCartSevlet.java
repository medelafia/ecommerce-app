package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

@WebServlet("/cart")
public class ViewCartSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession() ; 
		if(httpSession ==  null || httpSession.getAttribute("cart") == null ) { 
			httpSession.setAttribute("cart", new Cart())  ; 
		}
		Cart cart = (Cart) httpSession.getAttribute("cart") ; 
		req.setAttribute("cart", cart); 
		req.getRequestDispatcher("/views/client/Cart.jsp").forward(req, resp) ; 
	}
	
	
 }
