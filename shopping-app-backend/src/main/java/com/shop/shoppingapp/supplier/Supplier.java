package com.shop.shoppingapp.supplier;

import java.util.ArrayList;
import java.util.List;

import com.shop.shoppingapp.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "supplier_id")
	private Long id;

	@Column(name = "supplier_name")
	private String supplierName;
	private String city;
	@Column(name = "zip")
	private String zipCode;
	
	@OneToMany(mappedBy = "supplier")
	private List<Product> products = new ArrayList<Product>();
	
	public Supplier() {
		
	}
	
	public Supplier(String supplierName, String city, String zipCode) {
		this.setSupplierName(supplierName);
		this.setCity(city);
		this.setZipCode(zipCode);
	}
	
	public Long getSupplierID() {
		return id;
	}

	public void setSupplierID(Long supplierID) {
		this.id = supplierID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	@Override
	public String toString() {
		return "Supplier [supplierID=" + id + ", supplierName=" + supplierName + ", city=" + city + ", zipCode="
				+ zipCode + "]";
	}

	
}
