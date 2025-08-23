package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import dao.DaoClient;
import dao.DaoOrder;
import enums.ShippingStatus;

/**
 * Servlet implementation class AfficherCommandesServlet
 */
@WebServlet("/admin/clients")
public class AdminViewClientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoClient daoClient ; 
	

    public AdminViewClientsServlet() {
    	this.daoClient = new DaoClient() ; 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("clients", this.daoClient.getAll()  ) ; 

		
		request.getRequestDispatcher("/views/admin/Clients.jsp").forward(request, response); ;
	}


}
