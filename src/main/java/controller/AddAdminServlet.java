package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Product;
import utils.MailUtils;
import utils.Utils;

import java.io.IOException;
import java.util.UUID;

import dao.DaoAdmin;

@WebServlet("/admin/addAdmin")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoAdmin daoCommercial ; 

    public AddAdminServlet() {
        this.daoCommercial = new DaoAdmin() ; 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom") ; 
		String email = request.getParameter("email")  ; 
		
		
		if(Utils.verifyField(nom) && Utils.verifyField(email))  { 
			try { 
				String mot_de_passe = UUID.randomUUID().toString() ; 
				this.daoCommercial.save(
						new Admin.Builder()
						.setEmail(email)
						.setNom(nom) 
						.setPassword(mot_de_passe) 
						.build()
						);
				
				
				try {
					MailUtils.sendMail(
							email ,
							"Hello " + nom + "you're now one of the ecommerce space admins \n your password is : " + mot_de_passe , 
							"E-commerce space admin team");
				} catch (Exception e) { 
					e.printStackTrace();
				}
				
				
				
				request.setAttribute("message", "user added succesfully") ;
			}catch (NumberFormatException exception ) {
				
				request.setAttribute("message", "invalid field format") ;
				
				
				
			}
		}else { 
			request.setAttribute("message", "all fields are required") ;
		}
		
		
		request.setAttribute("admins", this.daoCommercial.getAll() );
		request.getRequestDispatcher("/views/admin/Admins.jsp").forward(request, response) ;
	}

}
