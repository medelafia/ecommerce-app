package controller;

import java.io.IOException ;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.DaoOrder;
import enums.ShippingStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrderItem;
import model.Cart;
import model.Client;
import utils.SessionUtils;
import utils.Utils;

@WebServlet("/createOrder")
public class AddOrderServlet extends HttpServlet{
	
	private DaoOrder daoOrder ; 
	
	public AddOrderServlet() { 
		this.daoOrder = new DaoOrder(); 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/client/CreateOrder.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String shippingAddress = req.getParameter("shipping_address") ; 
		
		
		if(Utils.verifyField(shippingAddress)) { 
			
			Cart panier = (Cart)SessionUtils.getSessionAttribute(req , "cart" ) ; 	
			Client client = (Client)SessionUtils.getSessionAttribute(req , "client" ) ; 
			if(panier == null || client == null || panier.getPanierItems().isEmpty()) {
				resp.sendRedirect("/products");
				return ; 
			}
		
			List<OrderItem> orderItems = panier.getPanierItems()
					.stream()
					.map(item -> new OrderItem(item.getQte() , item.getProduit()))
					.toList() ; 
			
			Order order = new Order.Builder()
					.setTotalPrice( (float)panier.getTotal())
					.setShippingAddress(shippingAddress)
					.setShippingStatus(ShippingStatus.PENDING)
					.setOrderItems(orderItems)
					.setClient(client) 
					.setDate(Date.valueOf(LocalDate.now()))
					.build() ; 
					
					
			this.daoOrder.save(order) ;
			panier.emptyCart(); 
			
			resp.sendRedirect("/products");
			
				
			
		
		}
	}
}
