package com.shop.shoppingapp.supplier;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
	@Query("SELECT s FROM Supplier s WHERE s.supplierName = ?1") //use class name and variable names from class, not table
	Optional<Supplier> findSupplierByName(String name);
}
