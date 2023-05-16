package com.shop.shoppingapp.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api")
public class RatingController {
	
	private final RatingService ratingService;

	@Autowired
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@GetMapping(path = "product/{UPC}/rating")
	public List<Rating> getRatingByProductUPC(@PathVariable("UPC") String UPC){
		return ratingService.findRatingByProductUPC(UPC);
	}
	
	@GetMapping(path = "customer/{customerID}/rating")
	public List<Rating> getRatingByCustomerId(@PathVariable("customerID") Long customerId) {
		return ratingService.findRatingByCustomerId(customerId);
	}
	
	@PostMapping(path = "product/{UPC}/rating")
	public void addNewRating(@PathVariable("UPC") String UPC, 
			@RequestParam Long customerID, 
			@RequestBody Rating rating) {
		ratingService.addNewRating(UPC, customerID, rating);
	}
	
	@DeleteMapping(path = "rating/{ratingID}")
	public void deleteRating(@PathVariable("ratingID") Long ratingId) {
		ratingService.deleteRating(ratingId);
	}
	
	@PutMapping(path = "rating/{ratingID}")
	public void updateRating(@PathVariable("ratingID") Long ratingId,
			@RequestBody Rating rating) {
		ratingService.updateRating(ratingId, rating);
	}
}
