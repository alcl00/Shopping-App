package com.shop.shoppingapp.wish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api")
public class WishController {
	
	private final WishService wishService;

	@Autowired
	public WishController(WishService wishService) {
		this.wishService = wishService;
	}
	
	@PostMapping(path = "customer/{customerId}/wishlist")
	public void addToWishlist(@PathVariable("customerId") Long customerId, 
			@RequestParam String UPC) {
		wishService.addToWishlist(customerId, UPC);
	}
	
	@GetMapping(path = "customer/{customerId}/wishlist")
	public List<Wish> getWishList(@PathVariable("customerId") Long customerId) {
		return wishService.getCustomerWishlist(customerId);
	}
	
	@DeleteMapping(path = "customer/{customerId}/wishlist")
	public void deleteItemFromWishlist(@PathVariable("customerId") Long customerId, @RequestParam String UPC) {
		wishService.deleteFromWishlist(customerId, UPC);
	}
}
