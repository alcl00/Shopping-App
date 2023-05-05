package com.shop.shoppingapp.customer;

import java.util.ArrayList;
import java.util.List;

import com.shop.shoppingapp.orders.Orders;
import com.shop.shoppingapp.rating.Rating;
import com.shop.shoppingapp.wish.Wish;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "customerID")
	private Long id;

	@Column(name = "first_name")
	@NotNull
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@NotNull
	private String address;

	@NotNull
	private String city;
	@Column(name = "zip")
	@NotNull
	private String zipCode;
	
	@OneToMany(mappedBy = "customer")
	private List<Orders> orders = new ArrayList<Orders>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<Rating>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Wish> wishList = new ArrayList<Wish>();
	
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, String address, String city, String zipCode) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}


	public void setCustomerId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String toString() {
		return "Customer{" + 
			   "id:" + id + 
			   ", firstName: \'" + firstName + "\'" +
			   ", lastName: \'" + lastName + "\'" +
			   ", address: \'" + address + "\'" +
			   ", city: \'" + city + "\'" +
			   ", zipCode: \'" + zipCode + "\'}";
	}

}
