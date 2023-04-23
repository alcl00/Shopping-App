package com.shop.shoppingapp.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	public void updateCustomer(@PathVariable("customerID") Long customerId, 
			@RequestBody Customer customer) {
		
		System.out.println("New customer info: " + customer);
		
		Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer not found"));
		
		if(customer.getFirstName() != null && customer.getFirstName().length() > 0 && !Objects.equals(customerToUpdate.getFirstName(), customer.getFirstName())) {
			customerToUpdate.setFirstName(customer.getFirstName());
		}
		
		if(customer.getLastName() != null && customer.getLastName().length() > 0 && !Objects.equals(customerToUpdate.getLastName(), customer.getLastName())) {
			customerToUpdate.setLastName(customer.getLastName());
		}
		
		if(customer.getAddress() != null && customer.getAddress().length() > 0 && !Objects.equals(customerToUpdate.getAddress(), customer.getAddress())) {
			customerToUpdate.setAddress(customer.getAddress());
		}
		
		if(customer.getCity() != null && customer.getCity().length() > 0 && !Objects.equals(customerToUpdate.getCity(), customer.getCity())) {
			customerToUpdate.setCity(customer.getCity());
		}
		
		if(customer.getZipCode() != null && customer.getZipCode().length() > 0 && !Objects.equals(customerToUpdate.getZipCode(), customer.getZipCode())) {
			customerToUpdate.setZipCode(customer.getZipCode());
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
