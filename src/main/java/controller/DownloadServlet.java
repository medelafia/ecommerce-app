package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.DaoOrder;
import dao.DaoProduct;


@WebServlet("/admin/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoProduct daoProduct; 
    private DaoOrder daoOrder ; 

    public DownloadServlet() {
        this.daoOrder= new DaoOrder(); 
        this.daoProduct = new DaoProduct();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toDownload = request.getParameter("target") ;
		response.setContentType("text/csv");

		PrintWriter printWriter = response.getWriter() ; 
		switch (toDownload) {
		case "products": 
			List<Product> products = this.daoProduct.getAll() ; 
			for(Product product : products ) {   
				printWriter.write(product.toString()) ; 
			} 
			break ; 
		case "orders" : 
			List<Order> orders = this.daoOrder.getAll() ; 
			
			for(Order order : orders ) {   
				printWriter.write(order.toString()) ; 
			} 
			break ;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + toDownload);
		}
		printWriter.close() ; 
	}

}
