package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "produits") 
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id_produit  ; 
	private String nom ; 
	private String description ; 
	private double prix ; 
	private int quantite_stock ;
	private String imageUrl ; 
	
	public Product() { 
		
	}
	public Product(int id, String nom, String description, double prix, int quantite_stock , String imageUrl) {
		super();
		this.id_produit = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.quantite_stock = quantite_stock;
		this.imageUrl = imageUrl ; 
	}
	public Product(String nom, String description, double prix, int quantite_stock , String imageUrl ) {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.quantite_stock = quantite_stock;
		this.imageUrl = imageUrl ; 
	}
	public Product(Builder builder) { 
		this.id_produit = builder.id_produit ; 
		this.nom = builder.nom ; 
		this.description = builder.description ; 
		this.prix = builder.prix ; 
		this.quantite_stock = builder.quantite_stock ; 
		this.imageUrl = builder.imageUrl ; 
	}
	public int getId() {
		return id_produit;
	}
	public void setId(int id) {
		this.id_produit = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite_stock() {
		return quantite_stock;
	}
	public void setQuantite_stock(int quantite_stock) {
		this.quantite_stock = quantite_stock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	} 
	
	
	@Override
	public String toString() {
		
		return this.getId() + ',' +this.getNom() + ',' +  this.getDescription() + ',' + this.getPrix() + ',' + this.getQuantite_stock() +','+this.getImageUrl()+'\n';
	}
	
	public static class Builder { 
		private int id_produit  ; 
		private String nom ; 
		private String description ; 
		private double prix ; 
		private int quantite_stock ;
		private String imageUrl ; 
		
		
		public Builder setProductId(int id_produit) { 
			this.id_produit = id_produit ; 
			return this ; 
		}
		
		public Builder setNom(String nom) { 
			this.nom = nom ; 
			return this ; 
		}
		
		public Builder setDescription(String description) { 
			this.description = description ; 
			return this; 
		}
		public Builder setPrice(double price ) { 
			this.prix = price ; 
			return this ; 
		}
		public Builder setQte(int qte) { 
			this.quantite_stock = qte ; 
			return this ; 
		}
		public Builder setImageUrl(String imageUrl) { 
			this.imageUrl = imageUrl ; 
			return this ; 
		}
		public Product build() { 
			return new Product(this) ; 
 		}
		
		
	}
	
}
