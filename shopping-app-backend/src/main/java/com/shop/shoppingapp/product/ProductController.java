package com.shop.shoppingapp.product;

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
@RequestMapping("/api")
public class ProductController {
	
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping(path = "supplier/{supplierID}/product")
	public void addNewProduct(@PathVariable("supplierID") Long supplierID, 
							  @RequestBody Product product) {
		
		productService.addNewProduct(supplierID, product);
	}
	
	@DeleteMapping(path = "product/{UPC}")
	public void deleteProduct(@PathVariable("UPC") String UPC) {
		productService.deleteProduct(UPC);
	}
	
	@PutMapping(path = "product/{UPC}")
	public void updateProduct(@PathVariable("UPC") String UPC, 
			@RequestBody Product product) {
		productService.updateProduct(UPC, product);
	}
	
	@GetMapping(path = "product")
	public List<Product> getAllProducts() {
		return productService.findAllProducts();
	}
	
	@GetMapping(path = "supplier/{supplierID}/product")
	public List<Product> getProductsBySupplierID (@PathVariable("supplierID") Long supplierID) {
		return productService.findBySupplierId(supplierID);
	}
	
	@GetMapping(path = "product/{UPC}")
	public Product getProductByUPC(@PathVariable("UPC") String UPC) {
		return productService.findProductByUPC(UPC);
	}

}
