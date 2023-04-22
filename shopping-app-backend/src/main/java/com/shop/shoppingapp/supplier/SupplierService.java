package com.shop.shoppingapp.supplier;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {
	
	private final SupplierRepository supplierRepository;

	@Autowired
	public SupplierService(SupplierRepository supplierRepository) {
	
		this.supplierRepository = supplierRepository;
	}
	
	public void addNewSupplier(Supplier supplier) {
		if(supplierRepository.findSupplierByName(supplier.getSupplierName()).isPresent())
		{
			throw new IllegalStateException("Supplier with name " + supplier.getSupplierName() + " already exists");
		}
		
		supplierRepository.save(supplier);
	}
	
	public void deleteSupplier(Long supplierId) {
		
		if(!supplierRepository.existsById(supplierId)) {
			throw new IllegalStateException("Supplier with id " + supplierId + " not found");
		}
		
		supplierRepository.deleteById(supplierId);
	}
	
	@Transactional
	public void updateSupplier(Long supplierId, String supplierName, String city, String zipCode) {
		
		Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalStateException("Supplier not found"));
		
		
		if(supplierName != null && supplierName.length() > 0 && !Objects.equals(supplierName, supplier.getSupplierName())) {
			supplier.setSupplierName(supplierName);
		}
		
		if(city != null && city.length() > 0 && !Objects.equals(supplier.getCity(), city)) {
			supplier.setCity(city);
		}
		
		if(zipCode != null && zipCode.length() > 0 && !Objects.equals(supplier.getZipCode(), zipCode)) {
			supplier.setZipCode(zipCode);
		}
	}
	
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}
	
	public Supplier getSupplierById(Long id) {
		
		Optional<Supplier> supplierMaybe = supplierRepository.findById(id);
		
		if(supplierMaybe.isEmpty()) {
			throw new IllegalStateException("Supplier not found");
		}
		
		return supplierMaybe.get();
	}

}
