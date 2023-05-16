package com.shop.shoppingapp.rating;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

	//customer_product
	
	@Id
	@Column(name = "ratingID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "customerID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
	@ManyToOne
    @JoinColumn(name = "UPC")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	private Integer rating;
	@Column(name = "rating_date")
	private LocalDate ratingDate;
	
	
	public Rating() {
		this.ratingDate = LocalDate.now();
	}
	
	public Rating(Customer customer, Product product, Integer rating) {

		this.customer = customer;
		this.product = product;
		this.rating = rating;
		this.ratingDate = LocalDate.now();
	}
	
	public Rating(Customer customer, Product product, Integer rating, LocalDate ratingDate) {

		this.customer = customer;
		this.product = product;
		this.rating = rating;
		this.ratingDate = ratingDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public LocalDate getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(LocalDate ratingDate) {
		this.ratingDate = ratingDate;
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
	
	
}
