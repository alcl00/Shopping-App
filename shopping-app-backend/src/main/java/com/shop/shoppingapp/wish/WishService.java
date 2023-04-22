package com.shop.shoppingapp.wish;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.customer.CustomerRepository;
import com.shop.shoppingapp.product.Product;
import com.shop.shoppingapp.product.ProductRepository;

@Service
public class WishService {
	
	private final WishRepository wishRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	
	@Autowired
	public WishService(WishRepository wishRepository, ProductRepository productRepository,
			CustomerRepository customerRepository) {
		this.wishRepository = wishRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}
	
	public void addToWishlist(Long customerId, String UPC) {
		Optional<Customer> customerMaybe = customerRepository.findById(customerId);
		
		if(customerMaybe.isEmpty()) {
			throw new IllegalStateException("Customer with ID " + customerId + " does not exist");
		}
		
		Optional<Product> productMaybe = productRepository.findById(UPC);
		
		if(productMaybe.isEmpty()) {
			throw new IllegalStateException("Product with UPC :" + UPC + " not found");
		}
		
		wishRepository.save(new Wish(customerMaybe.get(), productMaybe.get()));
	}
	
	public void deleteFromWishlist(Long customerId, String UPC) {
		
		Optional<Customer> customerMaybe = customerRepository.findById(customerId);
		
		if(customerMaybe.isEmpty()) {
			throw new IllegalStateException("Customer with ID " + customerId + " does not exist");
		}
		
		Optional<Product> productMaybe = productRepository.findById(UPC);
		
		if(productMaybe.isEmpty()) {
			throw new IllegalStateException("Product with UPC :" + UPC + " not found");
		}
		
		Iterator<Wish> it = wishRepository.findByCustomerId(customerId).iterator();
		
		while(it.hasNext()) {
			Wish w = it.next();
			if(w.getCustomer().getId() == customerId && w.getProduct().getUPC() == UPC) {
				wishRepository.deleteById(w.getId());
				break;
			}
		}
	}
	
	public List<Wish> getCustomerWishlist(Long customerId) {
		
		Optional<Customer> customerMaybe = customerRepository.findById(customerId);
		
		if(customerMaybe.isEmpty()) {
			throw new IllegalStateException("Customer with ID " + customerId + " does not exist");
		}
		
		return wishRepository.findByCustomerId(customerId);
	}
}
