package controller;

import java.io.IOException;
import java.util.Optional;

import dao.DaoClient;
import dao.DaoAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Client;
import model.Admin;
import utils.MailUtils;
import utils.Utils;

@WebServlet("/login")
public class AuthentificationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DaoClient daoClient  ;
	private DaoAdmin daoCommercial ; 
	@Override
	public void init() throws ServletException {
		super.init();
		this.daoClient  = new DaoClient() ; 
		this.daoCommercial = new DaoAdmin() ; 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();  
		String type = req.getParameter("type");
		if(httpSession != null  ) { 
			if(httpSession.getAttribute("client") != null || httpSession.getAttribute("commercial") != null) { 
				resp.sendRedirect("/index");
				return ;
			}
		}
		req.setAttribute("type", type)  ; 
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp) ; 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type"); 
		String login = req.getParameter("login"); 
		String motDePasse = req.getParameter("motDePasse"); 
		HttpSession httpSession = req.getSession() ; 
		
		if(( type.equals("client") 
				|| type.equals("commercial") ) 
				&& Utils.verifyField(login)
				&& Utils.verifyField(motDePasse)
				)  {
			
			if(type.equals("client")) { 
				Optional<Client> optClient = daoClient.getByEmailAndPassword(login, motDePasse) ; 
				if(optClient.isPresent() ) {
					/*if(optClient.get().isAccount_verified()) { */
					httpSession.setAttribute("client", optClient.get());
					resp.sendRedirect("/index"); 	
					return ; 
					/*} else  { 
						
						String email = optClient.get().getEmail() ; 
						try {
							
							MailUtils.sendMail(
									email ,
									"Congrats you are just registred on ecommerce space \n please verify your account : "+Utils.getUrl(req)+"/VerifyAccount?email=" +email  , 
									"Account Verifcation");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						req.setAttribute("message", "you account is not verified , please check your email and follow the link to verify it ! ") ; 
						req.setAttribute("type", "client")  ; 
						req.getRequestDispatcher("/views/login.jsp").forward(req, resp) ; 
					}*/
				}
				
			}else if(type.equals("commercial")){ 
				Optional<Admin> optCommercial = daoCommercial.getByEmailAndPassword(login, motDePasse) ; 
				if(optCommercial.isPresent() ) {
					httpSession.setAttribute("commercial", optCommercial.get());
					resp.sendRedirect("/admin/dashboard"); 	
					return ; 
				}
			}
			req.setAttribute("message", "invalid login or password") ; 
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp) ; 
			
		}
		

	}
}
