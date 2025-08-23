package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import utils.S3Service;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;


@WebServlet("/image")
public class ImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private S3Service s3Service ; 
	
    public ImagesServlet() {
        this.s3Service = new S3Service() ; 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageKey = request.getParameter("key") ; 
		
		
		if(Utils.verifyField(imageKey) )  {
			
			ResponseInputStream<GetObjectResponse> responseInputStream = this.s3Service.getImage(imageKey ) ; 
			response.setContentType(responseInputStream.response().contentType());

			 
			response.getOutputStream().write(responseInputStream.readAllBytes()) ; 
 		}
		
		
	}

}
