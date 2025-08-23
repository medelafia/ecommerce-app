package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Client;
import model.Order;
import utils.SessionUtils;

@WebServlet("/orders")
public class ViewOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoOrder daoOrder ; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoOrder = new DaoOrder(); 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client authenticatedClient = (Client)SessionUtils.getSessionAttribute(req, "client") ; 
		List<Order> orders = new ArrayList<Order>() ; 
		
		if( authenticatedClient != null ) { 
			orders = this.daoOrder.getOrdersByClientId(authenticatedClient.getId()) ; 
		}
		
		req.setAttribute("orders", orders ); 
		req.getRequestDispatcher("/views/client/Orders.jsp").forward(req, resp) ; 
	}
	
	
 }
