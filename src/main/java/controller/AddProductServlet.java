package controller;

import java.awt.im.InputContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Product;
import utils.S3Service;
import utils.Utils;

@WebServlet("/admin/addProduct")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoProduct daoProduit ; 
	private S3Service s3Service  ; 
	@Override
	public void init() throws ServletException {
		super.init();
		
		this.daoProduit = new DaoProduct() ;  
		this.s3Service = new S3Service() ; 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nom = req.getParameter("nom") ; 
		String description = req.getParameter("description")  ; 
		String prix = req.getParameter("prix")  ; 
		String qte = req.getParameter("qte") ;  
		Part part  = req.getPart("image") ; 
		
		
		if(Utils.verifyField(nom) && Utils.verifyField(description) && Utils.verifyField(prix) && Utils.verifyField(qte) && part != null )  { 

			String imageKey = this.s3Service.upload(part) ; 
			
			try { 
			
				daoProduit.save(new Product.Builder()
						.setNom(nom )
						.setDescription(description ) 
						.setPrice(Double.parseDouble(prix)) 
						.setQte(Integer.parseInt(qte))
						.setImageUrl(String.format("/image?key=%s", imageKey))
						.build()
						);
				
				
				req.setAttribute("message", "product added succesfully") ;
				
			}catch (NumberFormatException exception ) {
				
				req.setAttribute("message", "invalid field format") ;
				
				
				
			}
		}else { 
			req.setAttribute("message", "all fields required") ;
		}
		
		
		resp.sendRedirect("/admin/products");
		
		
	} 
	
	
}
