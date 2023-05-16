package com.shop.shoppingapp.wish;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.product.Product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "wish")
public class Wish {

	@EmbeddedId
	private WishID id;
	
	@ManyToOne
	@MapsId("customerId")
	@JoinColumn(name = "customerID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
	@ManyToOne
	@MapsId("UPC")
	@JoinColumn(name = "UPC")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;


	public Wish() {
	}

	public Wish(Customer customer, Product product) {
		this.customer = customer;
		this.product = product;
		this.id = new WishID(customer.getId(), product.getUPC());
	}
	
	@JsonIgnore
	public WishID getId() {
		return id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Wish [customer=" + customer + ", product=" + product + "]";
	}

	
	
	
}
