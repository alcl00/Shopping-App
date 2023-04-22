package com.shop.shoppingapp.rating;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.customer.CustomerRepository;
import com.shop.shoppingapp.product.Product;
import com.shop.shoppingapp.product.ProductRepository;

@Service
public class RatingService {

	private final RatingRepository ratingRepository;
	
	private final ProductRepository productRepository;
	
	private final CustomerRepository customerRepository;

	@Autowired
	public RatingService(RatingRepository ratingRepository, ProductRepository productRepository,
			CustomerRepository customerRepository) {
		this.ratingRepository = ratingRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}
	
	public List<Rating> findRatingByCustomerId(Long customerId) {
		
		if(!customerRepository.existsById(customerId)) {
			throw new IllegalStateException("Customer with ID: " + customerId + " not found");
		}
		
		return ratingRepository.findRatingByCustomerId(customerId);
	}
	
	public List<Rating> findRatingByProductUPC(String UPC) {
		
		if(!productRepository.existsById(UPC))
		{
			throw new IllegalStateException("Product with UPC: " + UPC + " not found");
		}
		
		return ratingRepository.findRatingByProductUPC(UPC);
	}
	
	
	public void addNewRating(String UPC, Long customerId, Rating rating) {
		
		Optional<Product> productMaybe = productRepository.findById(UPC);
		
		if(productMaybe.isEmpty()) {
			throw new IllegalStateException("Product with UPC: " + UPC + " not found");
		}
		
		rating.setProduct(productMaybe.get());
		
		
		Optional<Customer> customerMaybe = customerRepository.findById(customerId);
		if(customerMaybe.isEmpty()) {
			throw new IllegalStateException("Customer with ID: " + customerId + " not found");
		}
		
		rating.setCustomer(customerMaybe.get());
		
		ratingRepository.save(rating);
	}
	
	public void deleteRating(Long ratingId) {
		
		if(!ratingRepository.existsById(ratingId)) {
			throw new IllegalStateException("Rating with ID: " + ratingId + "Not found");
		}
		
		ratingRepository.deleteById(ratingId);
	}
	
	@Transactional
	public void updateRating(Long ratingId,
							Integer rating) {
		
		Rating ratingToUpdate = ratingRepository.findById(ratingId).orElseThrow(() -> new IllegalStateException("Rating with ID: " + ratingId + "Not found"));
		
		System.out.println("Old rating: " + ratingToUpdate.getRating() + "\nNew Rating: " + rating);
		if(rating != null && !Objects.equals(rating, ratingToUpdate.getRating())) {
			ratingToUpdate.setRating(rating);
		}
	}
}
