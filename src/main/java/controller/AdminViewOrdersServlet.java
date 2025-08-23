package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import dao.DaoOrder;
import enums.ShippingStatus;

/**
 * Servlet implementation class AfficherCommandesServlet
 */
@WebServlet("/admin/orders")
public class AdminViewOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoOrder daoOrder ; 
	

    public AdminViewOrdersServlet() {
    	this.daoOrder = new DaoOrder() ; 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("orders", this.daoOrder.getAll()  ) ; 
		request.setAttribute("shippingStatusValues", Arrays.stream(ShippingStatus.values()).map(val -> val.name()).toList()) ; 
	
		
		request.getRequestDispatcher("/views/admin/Orders.jsp").forward(request, response); ;
	}


}
