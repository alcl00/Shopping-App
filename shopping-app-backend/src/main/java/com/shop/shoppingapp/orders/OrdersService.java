package com.shop.shoppingapp.orders;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.customer.CustomerRepository;

@Service
public class OrdersService {
	
	private final OrdersRepository ordersRepository;
	
	private final CustomerRepository customerRepository;

	@Autowired
	public OrdersService(OrdersRepository ordersRepository, CustomerRepository customerRepository) {
		
		this.ordersRepository = ordersRepository;
		this.customerRepository = customerRepository;
	}
	
	public List<Orders> findAllOrders() {
		return ordersRepository.findAll();
	}
	
	public List<Orders> findByCustomerId (Long customerId){
		return ordersRepository.findByCustomerId(customerId);
	}
	
	public Orders findyByOrderId(Long orderId) {
		
		Optional<Orders> ordersMaybe = ordersRepository.findById(orderId);
		
		if(ordersMaybe.isEmpty()) {
			throw new IllegalStateException("Order with ID: " + orderId + " not found");
		}
		
		return ordersMaybe.get();
	}
	
	
	public void addNewOrder(Long customerId, Orders order) {
		
		Optional<Customer> customerMaybe = customerRepository.findById(customerId);
		
		if(customerMaybe.isEmpty()) {
			throw new IllegalStateException("Customer with ID " + customerId + " does not exist");
		}
		
		order.setCustomer(customerMaybe.get());
		
		ordersRepository.save(order);
	}
	
	public void deleteOrder(Long orderId) {
		
		if(!ordersRepository.existsById(orderId)) {
			throw new IllegalStateException("Order with ID: " + orderId + " not found");
		}
		
		ordersRepository.deleteById(orderId);
	}
	
	@Transactional
	public void updateOrder(Long orderId,
			LocalDate orderDate, 
			LocalDate shipDate, 
			PaymentMethod paymentMethod, 
			String CCN) {
		
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with ID: " + orderId + " not found"));
		
		if(orderDate != null && !Objects.equals(orderDate, order.getOrderDate())) {
			order.setOrderDate(orderDate);
		}
		
		if(shipDate != null && !Objects.equals(shipDate, order.getShipDate())) {
			order.setShipDate(shipDate);
		}
		
		if(paymentMethod != null && !Objects.equals(paymentMethod, order.getPaymentMethod())) {
			order.setPaymentMethod(paymentMethod);
		}
		
		if(CCN != null && CCN.length() > 0 && !Objects.equals(CCN, order.getCCN())) {
			order.setCCN(CCN);
		}
	}
}
