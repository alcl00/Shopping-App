package com.shop.shoppingapp.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	List<Product> findBySupplierId(Long supplierID);
	
	Optional<Product> findByUPC(String UPC);
}
