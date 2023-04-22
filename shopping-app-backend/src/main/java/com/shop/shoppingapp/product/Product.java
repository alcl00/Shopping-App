package com.shop.shoppingapp.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.shop.shoppingapp.contents.Contents;
import com.shop.shoppingapp.rating.Rating;
import com.shop.shoppingapp.supplier.Supplier;
import com.shop.shoppingapp.wish.Wish;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "UPC")
	private String UPC; 
	@Column(name = "product_name")
	private String productName; 
	private Double price;
	
	
	@ManyToOne
    @JoinColumn(name = "supplier_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Supplier supplier;
	
	private Integer amount; 
	
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	
	private String category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL) //The value for mappedBy is the name of the instance of this class used in the Contains class
	private List<Contents> contains = new ArrayList<Contents>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<Rating>(); 
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Wish> wishes = new ArrayList<Wish>();
	
	
	public Product() {
		
	}
	
	
	public Product(String productName, Double price, Supplier supplier, Integer amount,
			Integer reorderLevel, String category) {
		this.productName = productName;
		this.price = price;
		this.supplier = supplier;
		this.amount = amount;
		this.reorderLevel = reorderLevel;
		this.category = category;
	}
	
	public String getUPC() {
		return UPC;
	}
	public void setUPC(String UPC) {
		this.UPC = UPC;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [UPC=" + UPC + ", productName=" + productName + ", price=" + price + ", supplier=" + supplier
				+ ", amount=" + amount + ", reorderLevel=" + reorderLevel + ", category=" + category + "]";
	}
}
