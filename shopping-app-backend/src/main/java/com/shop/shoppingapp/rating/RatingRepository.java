package com.shop.shoppingapp.rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	List<Rating> findRatingByCustomerId(Long customerId);
	
	List<Rating> findRatingByProductUPC(String UPC);
}
