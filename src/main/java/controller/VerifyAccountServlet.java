package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import utils.Utils;

import java.io.IOException;
import java.util.Optional;

import com.mysql.cj.util.Util;

import dao.DaoClient;

/**
 * Servlet implementation class VerifyAccountServlet
 */
@WebServlet("/VerifyAccount")
public class VerifyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoClient daoClient ; 
    public VerifyAccountServlet() {
        this.daoClient = new DaoClient() ; 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email") ; 
		
		
		if(Utils.verifyField(email)) { 
			
			Optional<Client> clientOptional = daoClient.getByEmail(email) ; 
			
			if(clientOptional.isPresent()) { 
				Client client = clientOptional.get() ; 
				
				client.setAccount_verified(true); 
			
				this.daoClient.update(client);
				
				
				client.setMot_de_passe(null);
				request.getSession().setAttribute("client", client); 
				
				
				response.sendRedirect("/index");
			}else {
				response.sendRedirect("/login");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
