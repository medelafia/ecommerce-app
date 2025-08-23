package controller;

import java.io.IOException;

import dao.DaoClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import utils.MailUtils;
import utils.Utils;

@WebServlet("/register")
public class ClientRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoClient daoClient ; 
	
	
	@Override
	public void init() throws ServletException {
		this.daoClient = new DaoClient();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp) ; 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = req.getParameter("nom") ; 
		String email = req.getParameter("email") ; 
		String motDePasse = req.getParameter("motDePasse") ; 
		
		
		
		if(Utils.verifyField(nom) && Utils.verifyField(email) && Utils.verifyField(motDePasse)) { 
			
			if(!this.daoClient.getByEmail(email).isPresent()) { 
				daoClient.save(
						new Client.Builder()
						.setAccount_verified(false)
						.setEmail(email)
						.setNom(nom)
						.setPassword(motDePasse)
						.build()
						);
				
				resp.sendRedirect("/login?type=client");
				return; 
				/* MailUtils.sendMail(
						email, 
						"Congrats you are just registred on ecommerce space \n please verify your account :" +Utils.getUrl(req)+"/VerifyAccount?email=" +email  , 
						"Account Verifcation");*/
						
			}else  { 
				req.setAttribute("message", "the email is already used by an account") ; 
			}			
		}else { 
			req.setAttribute("message", "invalid fields") ; 
		}
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
}	
