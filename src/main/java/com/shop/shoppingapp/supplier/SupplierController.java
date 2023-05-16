package com.shop.shoppingapp.supplier;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/supplier")
public class SupplierController {
	
	private SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {

		this.supplierService = supplierService;
	}
	
	@GetMapping
	public List<Supplier> getAllSuppliers() {
		return supplierService.getAllSuppliers();
	}
	
	@GetMapping(path = "{supplierID}")
	public Supplier getSupplierById(@PathVariable("supplierID") Long id) {
		return supplierService.getSupplierById(id);
	}
	
	@PostMapping
	public void addNewSupplier(@RequestBody Supplier supplier) {
		supplierService.addNewSupplier(supplier);
	}
	
	@DeleteMapping(path = "{supplierID}")
	public void deleteSupplier(@PathVariable("supplierID") Long id) {
		supplierService.deleteSupplier(id);
	}
	
	@PutMapping("{supplierID}")
	public void updateSupplier(@PathVariable("supplierID") Long id, 
								@RequestBody Supplier supplier) {
		supplierService.updateSupplier(id, supplier);
	}
	
	
}
