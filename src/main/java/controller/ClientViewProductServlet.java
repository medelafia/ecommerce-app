package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import utils.Utils;

import java.io.IOException;
import java.util.Optional;

import dao.DaoProduct;


@WebServlet("/product")
public class ClientViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoProduct daoProduct ; 
    public ClientViewProductServlet() {
        this.daoProduct = new DaoProduct() ; 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("id")  ; 
		
		
		if(Utils.verifyField(productId) ) { 
			try { 
				Optional<Product> product = this.daoProduct.getById(Integer.parseInt(productId)); 
				
				
				if(product.isPresent() ) { 
					request.setAttribute("product", product.get());
					
					request.getRequestDispatcher("/views/client/Product.jsp").forward( request, response) ; 
				}
			}catch (NumberFormatException numberFormatException ) {
						
			}
		}
	}

}
