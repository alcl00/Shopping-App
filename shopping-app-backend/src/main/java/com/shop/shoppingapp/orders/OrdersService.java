package com.shop.shoppingapp.orders;

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
			Orders order) {
		
		Orders orderToUpdate = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with ID: " + orderId + " not found"));
		
		if(order.getOrderDate() != null && !Objects.equals(orderToUpdate.getOrderDate(), order.getOrderDate())) {
			orderToUpdate.setOrderDate(order.getOrderDate());
		}
		
		if(order.getShipDate() != null && !Objects.equals(orderToUpdate.getShipDate(), order.getShipDate())) {
			orderToUpdate.setShipDate(order.getShipDate());
		}
		
		if(order.getPaymentMethod() != null && !Objects.equals(orderToUpdate.getPaymentMethod(), order.getPaymentMethod())) {
			orderToUpdate.setPaymentMethod(order.getPaymentMethod());
		}
		
		if(order.getCCN() != null && order.getCCN().length() > 0 && !Objects.equals(orderToUpdate.getCCN(), order.getCCN())) {
			orderToUpdate.setCCN(order.getCCN());
		}
	}
}
