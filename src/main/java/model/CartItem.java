package model;

public class CartItem {

	private Product produit ; 
	private int qte ; 
	
	public CartItem() {} 
	public CartItem(Product produit, int qte ) { 
		this.produit = produit ; 
		this.qte  = qte ;  
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
}
