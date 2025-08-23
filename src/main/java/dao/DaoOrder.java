package dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.Query;
import model.Order;
import utils.HibernateUtil;

public class DaoOrder extends CrudDao<Order>{
	
	
	
	private SessionFactory sessionFactory  ; 
	

	
	public DaoOrder() { 
		super(Order.class) ; 
		this.sessionFactory = HibernateUtil.getSessionFactory() ; 
	}

	public List<Order> getOrdersByClientId(int id) { 
		try(Session session = this.sessionFactory.openSession()){ 
			Query query = session.createQuery("FROM Order o where o.client.id=:id" , Order.class) ; 
			
			query.setParameter("id", id) ; 
			
			return query.getResultList() ; 
		}catch (HibernateException hibernateException) {
			return List.of() ; 
		}
	}
	public Long getOrdersCountByClientId(int id) { 
		try(Session session = this.sessionFactory.openSession()){ 
			Query query = session.createQuery("SELECT COUNT(o.id) FROM Order o where o.client.id=:id" , Long.class) ; 
			
			query.setParameter("id", id) ; 
			
			return (Long)query.getSingleResult(); 
		}catch (HibernateException hibernateException) {
			return 0l ; 
		}
	}
	
}
