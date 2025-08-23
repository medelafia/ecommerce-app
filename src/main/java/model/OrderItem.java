package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity 
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  
	private int qte ; 
	
	@OneToOne
	private Product produit ; 
	
	
	public OrderItem() { } 
	public OrderItem(int qte , Product produit) { 
		this.qte = qte; 
		this.produit = produit; 
	}
	
	
	public Product getProduit() {
		return produit;
	}
	public void setProduit(Product produit) {
		this.produit = produit;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public int getId() {
		return id;
	}
}
