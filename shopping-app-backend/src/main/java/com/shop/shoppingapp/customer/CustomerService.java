package com.shop.shoppingapp.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;	
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public void addNewCustomer(Customer customer) {
		
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long id) {
		
		if(!customerRepository.existsById(id)) {
			throw new IllegalStateException("Customer with ID " + id + " does not exist");
		}
		customerRepository.deleteById(id);
	}
	
	@Transactional
	public void updateCustomer(Long customerId, String firstName, String lastName, String address, String city, String zipCode) {
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer not found"));
		
		if(firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName)) {
			customer.setFirstName(firstName);
		}
		
		if(lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName)) {
			customer.setFirstName(lastName);
		}
		
		if(address != null && address.length() > 0 && !Objects.equals(customer.getAddress(), address)) {
			customer.setAddress(address);
		}
		
		if(city != null && city.length() > 0 && !Objects.equals(customer.getCity(), city)) {
			customer.setCity(city);
		}
		
		if(zipCode != null && zipCode.length() > 0 && !Objects.equals(customer.getZipCode(), zipCode)) {
			customer.setZipCode(zipCode);
		}
		
	}
	
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer findCustomerById(Long id) {
		
		Optional<Customer> customerMaybe = customerRepository.findById(id);
		
		if(customerMaybe.isEmpty())
		{
			throw new IllegalStateException("Customer with id " + id + " not found");
		}
		
		return customerMaybe.get();
	}
	
}
