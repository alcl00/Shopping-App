package com.shop.shoppingapp.orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.shop.shoppingapp.contents.Contents;
import com.shop.shoppingapp.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "orderID")
	private Long id;
	@Column(name = "order_date")
	private LocalDate orderDate;
	@Column(name = "ship_date")
	private LocalDate shipDate;
	
	@Column(name = "payment_method", columnDefinition = "ENUM('VISA', 'MC', 'DISCOVER')")
    @Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod; 
	private String CCN;
	
	@OneToMany(mappedBy = "orders")
	private List<Contents> contents = new ArrayList<Contents>();
	
	@ManyToOne
    @JoinColumn(name = "customerID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
	public Orders() {
		
	}
	
	public Orders(Customer customer, LocalDate orderDate, PaymentMethod paymentMethod, String CCN) {
		
		this.customer = customer;
		this.orderDate = orderDate;
		this.paymentMethod = paymentMethod;
		this.CCN = CCN;
	}

	public Orders(Customer customer, LocalDate orderDate, LocalDate shipDate, PaymentMethod paymentMethod, String CCN) {
		
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.paymentMethod = paymentMethod;
		this.CCN = CCN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderdate) {
		this.orderDate = orderdate;
	}

	public LocalDate getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCCN() {
		return CCN;
	}

	public void setCCN(String CCN) {
		this.CCN = CCN;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Contents> getContents() {
		return contents;
	}

	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}
	
	
	
}
