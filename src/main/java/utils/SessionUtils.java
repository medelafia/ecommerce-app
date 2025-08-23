package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
	

	public static Object getSessionAttribute(HttpServletRequest request , String key) { 
		HttpSession httpSession  =  request.getSession() ;
		if(httpSession != null ) { 
			return httpSession.getAttribute(key) ; 
 		}
		return null ; 
	}
	
	public static boolean setSessionAttribute(HttpServletRequest request , String key , Object value ) { 
		HttpSession httpSession  =  request.getSession() ; 
		if(httpSession == null) return false; 
		httpSession.setAttribute(key, value ); 
		
		return true ; 
	}
	
}
