package com.shop.shoppingapp.contents;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.shop.shoppingapp.orders.Orders;
import com.shop.shoppingapp.product.Product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "contents")
public class Contents {
	
	
	@EmbeddedId
	private ContentsId id;
	
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "orderID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Orders orders;
	
	@ManyToOne
	@MapsId("UPC")
	@JoinColumn(name = "UPC")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	private int quantity;
	
	public Contents() {
		super();
	}

	public Contents(Orders orders, Product product, int quantity) {
		super();
		this.id = new ContentsId(orders.getId(), product.getUPC());
		this.orders = orders;
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
