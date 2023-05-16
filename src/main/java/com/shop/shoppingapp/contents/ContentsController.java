package com.shop.shoppingapp.contents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class ContentsController {
	
	private final ContentsService contentsService;

	@Autowired
	public ContentsController(ContentsService contentsService) {
		this.contentsService = contentsService;
	}
	
	@GetMapping(path = "{orderId}/contents")
	public List<Contents> getContentsByOrderId(@PathVariable("orderId") Long orderId) {
		return contentsService.findContentsByOrderId(orderId);
	}
	
}
