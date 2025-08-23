package dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import model.Product;
import utils.HibernateUtil;

public class DaoProduct extends CrudDao<Product>{

	private SessionFactory sessionFactory ; 
	public DaoProduct() { 
		super(Product.class) ; 
		this.sessionFactory = HibernateUtil.getSessionFactory() ; 
	}

	public List<Product> getAllContains(String searchKey) {
		try(Session session = this.sessionFactory.openSession()) { 
 			Query<Product> query =  session.createQuery("FROM Product as p where p.nom LIKE :first OR p.description LIKE :second" , Product.class);
 			searchKey = "%" + searchKey + "%" ; 
 			query.setParameter("first" , searchKey ) ; 
 			query.setParameter("second", searchKey ) ; 
  			
 			
 			
 			return query.list() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return null  ;
	}
	public List<Object[]> getMostSelledProduct() { 
		try(Session session = this.sessionFactory.openSession()) {  
			Query<Object[]> query = session.createQuery(
					"select o.produit.nom , SUM(o.qte * p.prix) "
					+ "from OrderItem as o JOIN Product as p on o.produit.id_produit = p.id_produit "
					+ "Group by o.produit.nom" , Object[].class) ; 
 			return query.getResultList() ; 
 		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
 		return null  ;
	}
	
}
