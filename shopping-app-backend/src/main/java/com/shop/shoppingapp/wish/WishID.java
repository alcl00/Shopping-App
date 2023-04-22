package com.shop.shoppingapp.wish;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class WishID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long customerId;
	private String UPC;
	
	public WishID() {
		
	}
	
	public WishID(Long customerId, String UPC) {
		this.customerId = customerId;
		this.UPC = UPC;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String UPC) {
		this.UPC = UPC;
	}
	
	
	
}
