package model;

import java.sql.Date;
import java.util.List;

import enums.ShippingStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "orders")
public class Order {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	private Date date ; 
	private float totalPrice ; 
	private String shippingAddress ;  
	private ShippingStatus shippingStatus ;

	
	
	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER) 
	@JoinColumn(name = "order_id")
	private List<OrderItem>  orderItems ; 
	
	@ManyToOne 
	@JoinColumn(name="client_id" )
	private Client client ; 
	
	public float getTotalPrice() {
		return this.totalPrice ; 
	}
	
	public Order() {
		
	}
	public Order(Date date , float totalPrice , String shippingAddress , List<OrderItem> orderItems , Client client ) { 
		this.date= date ; 
		this.totalPrice = totalPrice ; 
		this.shippingAddress = shippingAddress ; 
		this.orderItems = orderItems ; 
		this.shippingStatus =  ShippingStatus.PENDING ; 
		this.client = client ; 
	}
	public Order(Builder builder) { 
		this.date= builder.date ; 
		this.totalPrice = builder.totalPrice ; 
		this.shippingAddress = builder.shippingAddress ; 
		this.orderItems = builder.orderItems ; 
		this.shippingStatus =  builder.shippingStatus; 
		this.client = builder.client ; 
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Client getClient() {
		return client;
	}
	
	@Override
	public String toString() {
		return 	this.getId() + ',' +
				this.date.toGMTString() + ',' +
				this.getTotalPrice() + ',' + 
				this.shippingAddress + ',' + 
				this.shippingStatus.name() + ',' + 
				this.getClient().getNom() + '\n' 
				;
	}
	public static class Builder { 
		private Date date ; 
		private float totalPrice ; 
		private String shippingAddress ;  
		private ShippingStatus shippingStatus ; 
		private List<OrderItem> orderItems ; 
		private Client client  ;
		
		public Builder setDate(Date date) {
			this.date = date;
			return this ; 
		}
		public Builder setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this ; 
		}
		public Builder setShippingStatus(ShippingStatus shippingStatus) {
			this.shippingStatus = shippingStatus;
			return this ; 
		}
		public Builder setTotalPrice(float totalPrice) {
			this.totalPrice = totalPrice;
			return this ; 
		}
		public Builder setClient(Client client) {
			this.client = client;
			return this ; 
		}
		public Builder setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
			return this ; 
		}
		public Order build() { 
			return new Order(this) ; 
		}
	}
	

	
	
}
