package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import model.Client;
import model.Admin;
import model.Product;
import utils.HibernateUtil;

public class DaoAdmin extends CrudDao<Admin>{
	
	
	private SessionFactory sessionFactory ; 
	
	
	public DaoAdmin() { 
		super(Admin.class) ; 
		this.sessionFactory = HibernateUtil.getSessionFactory() ; 
	}

	public Optional<Admin> getByEmailAndPassword(String email , String mdp  ) { 
		Optional optional = Optional.empty() ; 
 		try(Session session = this.sessionFactory.openSession()) { 
			
 			Query<Admin> query = session.createQuery("FROM Admin where email=:email AND mot_de_passe=:mdp", Admin.class ) ; 

			
			query.setParameter("email", email ); 
			query.setParameter("mdp" , mdp ) ;   
			 
			return query.uniqueResultOptional() ; 

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return optional ;
	}
  

	
}
