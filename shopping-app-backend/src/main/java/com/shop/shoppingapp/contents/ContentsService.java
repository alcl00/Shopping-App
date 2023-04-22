package com.shop.shoppingapp.contents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shoppingapp.orders.OrdersRepository;

@Service
public class ContentsService {
	
	private final ContentsRepository contentsRepository;
	
	private final OrdersRepository ordersRepository;

	@Autowired
	public ContentsService(ContentsRepository contentsRepository, OrdersRepository ordersRepository) {
		
		this.contentsRepository = contentsRepository;
		this.ordersRepository = ordersRepository;
	}
	
	public List<Contents> findContentsByOrderId(Long orderID) {
		
		if(!ordersRepository.existsById(orderID)) {
			throw new IllegalStateException("Order with ID: " + orderID + " not found");
		}
		
		return contentsRepository.findByOrdersId(orderID);
	}
	
	
}
