package model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	private String nom ; 
	private String email ; 
	private String mot_de_passe ;
	private boolean account_verified  ;

	
	public Client() { } 
	public Client(int id, String nom, String email, String mot_de_passe , boolean account_verified) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
		this.account_verified = account_verified ; 
	}
	public Client(Builder builder)  { 
		this.nom = builder.nom ;
		this.email = builder.email;
		this.mot_de_passe =  builder.mot_de_passe;
		this.account_verified =  builder.account_verified ; 
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
	public  boolean isAccountVerified() { 
		return this.account_verified ;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public boolean isAccount_verified() {
		return account_verified;
	}
	public void setAccount_verified(boolean account_verified) {
		this.account_verified = account_verified;
	} 
	
	
	public static class Builder  { 
		private String nom ; 
		private String email ; 
		private String mot_de_passe ;
		private boolean account_verified  ;
		
		
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
		public Builder setAccount_verified(boolean account_verified) {
			this.account_verified = account_verified;
			return this; 
		}
		public Client build() {
			return new Client(this) ; 
		}
	}
}
