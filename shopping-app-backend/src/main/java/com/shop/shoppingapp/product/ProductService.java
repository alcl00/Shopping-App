package com.shop.shoppingapp.product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shoppingapp.supplier.Supplier;
import com.shop.shoppingapp.supplier.SupplierRepository;

@Service
public class ProductService {
	
	
	private final ProductRepository productRepository;
	
	private final SupplierRepository supplierRepository;
	
	private ThreadLocalRandom random = ThreadLocalRandom.current();

	@Autowired
	public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
	
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> findBySupplierId(Long supplierId){
		if(!supplierRepository.existsById(supplierId)) {
			throw new IllegalStateException("Supplier with ID :" + supplierId + " not found");
		}
		
		return productRepository.findBySupplierId(supplierId);
	}
	
	public Product findProductByUPC(String UPC) {
		
		Optional<Product> productMaybe = productRepository.findById(UPC);
		
		if(productMaybe.isEmpty()) {
			throw new IllegalStateException("Product with UPC :" + UPC + " not found");
		}
		
		return productMaybe.get();
	}
	
	public void addNewProduct(Long supplierId, Product product) {
		
		Optional<Supplier> supplierMaybe = supplierRepository.findById(supplierId);
		
		if(supplierMaybe.isEmpty()) {
			throw new IllegalStateException("Supplier with ID: " + supplierId + " not found");
		}
		
		product.setSupplier(supplierMaybe.get());
		
		String UPC;
		do {
			UPC = generateUPC();
			
		} while(productRepository.existsById(UPC));
		
		product.setUPC(UPC);
		
		
		productRepository.save(product);
	}
	
	public void deleteProduct(String UPC) {
		
		if(!productRepository.existsById(UPC)) {
			throw new IllegalStateException("Product with UPC :" + UPC + " not found");
		}
		
		productRepository.deleteById(UPC);
	}
	
	
	@Transactional
	public void updateProduct(String UPC, String productName, Double price, Integer amount,
			Integer reorderLevel, String category) {
		
		Product product = productRepository.findById(UPC).orElseThrow(() -> new IllegalStateException("Product with UPC :" + UPC + " not found"));
		
		if(productName != null && productName.length() > 0 && !Objects.equals(productName, product.getProductName())) {
			product.setProductName(productName);
		}
		
		if(price != null && !Objects.equals(price, product.getPrice())) {
			product.setPrice(price);
		}
		
		if(amount != null && !Objects.equals(amount, product.getAmount())) {
			product.setAmount(amount);
		}
		
		if(reorderLevel != null && !Objects.equals(reorderLevel, product.getReorderLevel())) {
			product.setReorderLevel(reorderLevel);
		}
		
		if(category != null && category.length() > 0 && !Objects.equals(category, product.getCategory())) {
			product.setCategory(category);
		}
		
		//For future: change supplier
	}
	
	private String generateUPC() {
		 return String.valueOf(random.nextLong(10_000_000_000L, 100_000_000_000L));
	}
	
}
