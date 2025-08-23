package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class Utils {
	public static boolean verifyField(String field) { 
		return field != null && !field.isEmpty() ; 
	}
	public static String getUrl(HttpServletRequest request) { 
	    String scheme = request.getScheme(); // http or https
	    String host = request.getHeader("Host"); // this gives the ELB hostname:port
	    if (host == null) {
	        host = request.getServerName() + ":" + request.getServerPort();
	    }
	    return scheme + "://" + host;
	}
	

}
