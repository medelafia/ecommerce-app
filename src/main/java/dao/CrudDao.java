package dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Admin;
import model.Order;
import model.Product;
import utils.HibernateUtil;

public class CrudDao<T extends Object > implements Dao<T>{
	
	private SessionFactory sessionFactory  ; 
	private Class<T> type ; 

	public CrudDao(Class<T> type) { 
		this.sessionFactory = HibernateUtil.getSessionFactory() ; 
		this.type = type ; 
	}
	
	
	@Override
	public Optional<T> getById(int id) {
		Optional optional = Optional.empty() ; 
 		try(Session session = this.sessionFactory.openSession()) { 
 			T t = session.get(type , id  )  ; 
 			optional = Optional.of(t) ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
		
 		return optional ; 
	}

	@Override
	public List<T> getAll() {
		try(Session session = this.sessionFactory.openSession()) { 
 			return session.createQuery("from "+ type.getName() , type).list() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return null  ; 
	}

	@Override
	public void save(T t) {
		try(Session session = this.sessionFactory.openSession()) { 
			session.getTransaction().begin(); 
			session.persist(t) ;
			session.getTransaction().commit(); 
			
		}catch (HibernateException hibernateException ) {
			hibernateException.printStackTrace();
		}
	}

	@Override
	public void deleteById(int id) {
		try(Session session = this.sessionFactory.openSession()) { 
			session.getTransaction().begin(); 
			
			T t = session.get(type , id ) ; 
			
			session.remove(t);
			session.getTransaction().commit(); 
			
		}catch (HibernateException hibernateException ) {
			hibernateException.printStackTrace();
		} 
		
	}
	

	@Override
	@SuppressWarnings("deprecation")
	public void update(T t) {
		try(Session session = this.sessionFactory.openSession()) { 
			session.beginTransaction() ; 
			
			session.update(t);
			
			
			session.getTransaction().commit(); 
		}catch(HibernateException  hibernateException) {
			hibernateException.printStackTrace();
		}
	}
	
	
	@Override
	public Long getCount() { 
		try(Session session = this.sessionFactory.openSession()) { 
 			return session.createQuery("select count(*) from "+type.getName() , Long.class).uniqueResult() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return 0l  ; 
	}
	
	@Override
	public List<T> getPage(int page, int size) {
		try(Session session = this.sessionFactory.openSession()) { 
			Query query =  session.createQuery("from "+ type.getName() , type) ; 
			query.setFirstResult((page - 1) * size) ; 
			query.setMaxResults(size) ; 
			
 			return query.getResultList() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return null  ; 
	}

}
