package com.shop.shoppingapp.contents;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContentsId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long orderID;
	private String UPC;
	
	public ContentsId() {
		
	}

	public ContentsId(long orderID, String UPC) {
		super();
		this.orderID = orderID;
		this.UPC = UPC;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String UPC) {
		this.UPC = UPC;
	}

}
