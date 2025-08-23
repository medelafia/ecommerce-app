package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> panierItems ; 
	
	
	public Cart() {
		this.panierItems = new ArrayList<CartItem>() ; 
	}
	
	public void ajouterProduit(CartItem panierItem ) { 
		if( panierItem != null ) { 
			this.panierItems.add(panierItem) ; 
 		}else { 
 			throw new NullPointerException("le produit est null") ; 
 		}
	}
	
	public double getTotal() { 
		int total = 0  ; 
		for (CartItem panierItem : this.panierItems ) {
			total += panierItem.getProduit().getPrix() * panierItem.getQte();
		}
		return total  ; 
	}
	public List<CartItem> getPanierItems() {
		return panierItems;
	}
	public void  emptyCart() {  
		this.panierItems = List.of() ; 
	}
	public void deleteFromCard(int id) { 
		this.panierItems = new ArrayList<>(this.panierItems.stream().filter(item -> item.getProduit().getId() != id ).toList()); 
	}
}
