package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.Client.Builder;

@Entity 
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	public String nom ; 
	public String email ; 
	public String mot_de_passe ;
	
	
	public Admin() {} 
	public Admin(int id, String nom, String email, String mot_de_passe) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}
	public Admin(Builder builder)  { 
		this.nom = builder.nom ;
		this.email = builder.email;
		this.mot_de_passe =  builder.mot_de_passe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	} 
	public static class Builder  { 
		private String nom ; 
		private String email ; 
		private String mot_de_passe ;
		
		
		public Builder setNom(String nom) {
			this.nom = nom;
			return this; 
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this; 
		}
		public Builder setPassword(String mot_de_passe) {
			this.mot_de_passe = mot_de_passe;
			return this; 
		}
		public Admin build() {
			return new Admin(this); 
		}
	}
	
}
