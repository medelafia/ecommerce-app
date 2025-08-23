package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Path;
import dao.DaoProduct;


@WebServlet("/admin/importFromServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class ImportProductsFromCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private DaoProduct daoProduct ; 
	
	
	public ImportProductsFromCsvServlet() { 
		this.daoProduct = new DaoProduct(); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part part = request.getPart("file")  ; 
		
		
		if(part  != null ) { 
			String path = Path.of(request.getContextPath(), "tmp").toString()  ; 
			File tmpDir = new File(path)  ; 
		
			
			if(!tmpDir.exists())  { 
				tmpDir.mkdir() ; 
			}
			
		
			String filePath  = path.concat(part.getSubmittedFileName()) ; 
			
			File csvFile = new File(filePath) ; 
			
 			FileOutputStream fileOutputStream =  new FileOutputStream(csvFile) ; 
 			
 			fileOutputStream.write(part.getInputStream().readAllBytes()); 
 			
 			fileOutputStream.close();  
 			
 			
 			BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile)) ; 
 			String line ; 
 			int savedProducts = 0 ; 
 			int failedProducts  = 0 ; 
 			
 			while((line = bufferedReader.readLine()) != null ) { 
 				
 		
 				String[] values = line.split(",") ; 
 				if( values.length != 5 ) { 
 					failedProducts += 1 ; 
 					continue ;  
 				}
 				try {
	 				Product product = new Product(values[0] , values[1] , Double.parseDouble(values[2]) , Integer.parseInt(values[3]),values[4]  ) ; 
	 				daoProduct.save(product);
	 				
	 				savedProducts += 1 ;
				}catch (NumberFormatException | IndexOutOfBoundsException exception ) {
					failedProducts += 1 ; 
				}
 				
			}
 				
 			bufferedReader.close(); 
 			csvFile.delete() ; 
 			
 			request.setAttribute("message", String.format("Summary : %d uploaded successfully and %d failed" , savedProducts, failedProducts) );
 			
			
		}else   { 
			request.setAttribute("message", "invalid file");
		}
		
		response.sendRedirect("/admin/products");
		//request.setAttribute("products", this.daoProduct.getAll());
		//request.getRequestDispatcher("/views/admin/Products.jsp").forward(request, response) ; 
	}

}
