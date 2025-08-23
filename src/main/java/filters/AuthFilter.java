package filters;

import java.io.IOException;
import java.net.http.HttpRequest;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"} )
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)arg0 ;  
		HttpServletResponse httpServletResponse = (HttpServletResponse)arg1 ; 
		
		String  url  =  httpRequest.getRequestURI() ; 
		
		if(url.startsWith("/admin") ) { 
			HttpSession httpSession = httpRequest.getSession() ;
			
			
			
			if(httpSession.getAttribute("commercial") != null ) { 
				arg2.doFilter(arg0, arg1) ; 
				return ; 
			}else if(httpSession.getAttribute("client") != null ){ 
				httpServletResponse.sendRedirect("/accessDenied");
				return ; 
			}else  { 
				httpServletResponse.sendRedirect("/login?type=commercial");
				return ; 
			}
			
		}else if(url.equals("/createOrder") || url.equals("/addToCart") ||  url.equals("/cart") || url.equals("/orders")) { 
			HttpSession httpSession = httpRequest.getSession() ;
		
			
			if(httpSession.getAttribute("client") != null ) { 
				arg2.doFilter(arg0, arg1) ; 
				return ; 
			}else if(httpSession.getAttribute("commercial") != null ){ 
				httpServletResponse.sendRedirect("/accessDenied");
				return ; 
			}else { 
				httpServletResponse.sendRedirect("/login?type=client");
				return ; 
			}
		}
		
		arg2.doFilter(arg0, arg1)  ; 

	}
	
}
