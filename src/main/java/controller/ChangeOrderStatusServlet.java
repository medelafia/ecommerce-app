package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import utils.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import dao.DaoOrder;
import enums.ShippingStatus;


@WebServlet("/admin/changeOrderStatus")
public class ChangeOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoOrder daoOrder ; 
	
    public ChangeOrderStatusServlet() {
        this.daoOrder = new DaoOrder(); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id") ; 
		String newStatus = request.getParameter("new_status") ;
		String message = null ; 
		
		if(Utils.verifyField(newStatus) && Utils.verifyField(id)) { 
			try { 
				Optional<Order> orderOptional = this.daoOrder.getById(Integer.parseInt(id)) ; 
				if(orderOptional.isPresent()) { 
					Order order = orderOptional.get() ; 
					order.setShippingStatus(ShippingStatus.valueOf(newStatus));
					this.daoOrder.update(order) ;
				}else { 
					message = "the order not found"; 
				}
			} catch (NumberFormatException ex) {
				message = "cannot convert non numerical  value to int" ; 
			}
			
		}else { 
			message = "invalid request parameters" ;
		}
		
		request.setAttribute("orders", this.daoOrder.getAll()  ) ; 
		request.setAttribute("shippingStatusValues", Arrays.stream(ShippingStatus.values()).map(val -> val.name()).toList()) ; 
		
		
		request.getRequestDispatcher("/views/admin/Orders.jsp").forward(request, response); ;
	}

}
