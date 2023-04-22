package com.shop.shoppingapp.orders;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class OrdersController {

	private final OrdersService ordersService;

	@Autowired
	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	@PostMapping(path = "customer/{customerID}/orders")
	public void addNewOrder(@PathVariable("customerID") Long customerID, @RequestBody Orders order) {
		ordersService.addNewOrder(customerID, order);
	}
	
	@DeleteMapping(path = "orders/{orderID}") 
	public void deleteOrder(@PathVariable("orderID") Long orderID){
		ordersService.deleteOrder(orderID);
	}
	
	@GetMapping(path = "orders") 
	public List<Orders> getAllOrders(){
		return ordersService.findAllOrders();
	}
	
	@GetMapping(path = "customer/{customerID}/orders") 
	public List<Orders> getOrderByCustomerId(@PathVariable("customerID") Long customerID) {
		return ordersService.findByCustomerId(customerID);
	}
	
	@GetMapping(path = "orders/{orderID}")
	public Orders getOrderById(@PathVariable("orderID") Long orderID) {
		return ordersService.findyByOrderId(orderID);
	}
	
	
	@PutMapping("orders/{orderID}")
	public void updateOrder(@PathVariable("orderID") Long orderId,
			@RequestParam(required = false) LocalDate orderDate, 
			@RequestParam(required = false) LocalDate shipDate, 
			@RequestParam(required = false) PaymentMethod paymentMethod, 
			@RequestParam(required = false) String CCN) {
		ordersService.updateOrder(orderId, orderDate, shipDate, paymentMethod, CCN);
	}
}
