package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import model.Client;
import model.Product;
import utils.HibernateUtil;


public class DaoClient extends CrudDao<Client>{
	
	
	private SessionFactory sessionFactory  ; 
	

	
	public DaoClient() { 
		super(Client.class) ; 
		this.sessionFactory = HibernateUtil.getSessionFactory() ; 
	}

	public Optional<Client> getByEmailAndPassword(String email , String mdp) {

		try(Session session = this.sessionFactory.openSession()) { 
 			Query<Client> query =  session.createQuery("FROM Client as c where c.email=:first and c.mot_de_passe=:second" , Client.class);

 			query.setParameter("first" , email ) ; 
 			query.setParameter("second" , mdp ) ; 
 			
 			
 			return query.uniqueResultOptional() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return Optional.empty()  ;
	}
	
	
	public Optional<Client> getByEmail(String email ) {

		try(Session session = this.sessionFactory.openSession()) { 
 			Query<Client> query =  session.createQuery("FROM Client as c where c.email=:first" , Client.class);

 			query.setParameter("first" , email ) ; 
 			
 			
 			return query.uniqueResultOptional() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return Optional.empty()  ;
	}
	
	

	

}
