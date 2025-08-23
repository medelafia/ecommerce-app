package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import dao.DaoClient;
import dao.DaoOrder;
import dao.DaoProduct;


@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private DaoClient daoClient ; 
	private DaoProduct daoProduit  ; 
	private DaoOrder  daoOrder ;
    public DashboardServlet() {
    
        this.daoClient = new DaoClient() ; 
        this.daoProduit = new DaoProduct(); 
        this.daoOrder = new DaoOrder(); 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long nbProducts = this.daoProduit.getCount() ; 
		long nbClients = this.daoClient.getCount() ; 
		long nbOrders = this.daoOrder.getCount() ;
		
		List<Object[]> mostSelledProducts = this.daoProduit.getMostSelledProduct() ; 
		String[] mostSelledProductsNames = mostSelledProducts.stream().map(value -> { return (String)value[0];}).toArray(String[]::new); 
		Double[] mostSelledProductsIncomes = mostSelledProducts.stream().map(value -> { return (double)value[1];}).toArray(Double[]::new); 
		
		
		
		
		
		request.setAttribute("nbProducts", nbProducts );
		request.setAttribute("nbClients", nbClients );
		request.setAttribute("nbOrders", nbOrders );
		
		request.setAttribute("mostSelledProductsNames", mostSelledProductsNames);
		request.setAttribute("mostSelledProductsIncomes", mostSelledProductsIncomes);
		
		request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response) ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		doGet(request, response);
	}

}
