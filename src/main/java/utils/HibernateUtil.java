package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	
	private static SessionFactory  sessionFactory = null ; 
	public static SessionFactory getSessionFactory() { 		
		if(sessionFactory == null ) { 
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml") ;

			System.out.println(configuration.getProperty("hibernate.show_sql"));
			configuration.setProperty(
					"hibernate.connection.url" , 
					String.format("jdbc:mysql://%s:%s/%s" , System.getenv("MYSQL_HOST")  ,System.getenv("MYSQL_PORT")  ,System.getenv("MYSQL_DATABASE") )) ; 
			
			configuration.setProperty("hibernate.connection.username", System.getenv("MYSQL_USER")) ; 
			configuration.setProperty("hibernate.connection.password" , System.getenv("MYSQL_PASSWORD")) ; 
			
			sessionFactory = configuration.buildSessionFactory() ;  
		}
		return sessionFactory  ;
	}
	
	
}
 