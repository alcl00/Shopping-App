package com.shop.shoppingapp.wish;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, WishID>{
	
	List<Wish> findByCustomerId(Long customerId);
}
