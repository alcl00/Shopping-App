package com.shop.shoppingapp.configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.shoppingapp.contents.Contents;
import com.shop.shoppingapp.contents.ContentsRepository;
import com.shop.shoppingapp.customer.Customer;
import com.shop.shoppingapp.customer.CustomerRepository;
import com.shop.shoppingapp.orders.Orders;
import com.shop.shoppingapp.orders.OrdersRepository;
import com.shop.shoppingapp.orders.PaymentMethod;
import com.shop.shoppingapp.product.Product;
import com.shop.shoppingapp.product.ProductRepository;
import com.shop.shoppingapp.rating.Rating;
import com.shop.shoppingapp.rating.RatingRepository;
import com.shop.shoppingapp.supplier.Supplier;
import com.shop.shoppingapp.supplier.SupplierRepository;
import com.shop.shoppingapp.wish.Wish;
import com.shop.shoppingapp.wish.WishRepository;

@Configuration
public class Config {
	
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository, 
			ProductRepository productRepository, 
			SupplierRepository supplierRepository,
			OrdersRepository ordersRepository,
			ContentsRepository contentsRepository,
			RatingRepository ratingRepository,
			WishRepository wishRepository) {
		
		return args -> {
			System.out.println("Adding customers");
			Customer c1 = new Customer("Alex", "Levinson", "123 Main Street", "East Windsor", "08520");
			Customer c2 = new Customer("Homer", "Simpson", "742 Evergreen Terrace", "Springfield", "508008");
			customerRepository.saveAll(List.of(c1, c2));
			
			
			System.out.println("Adding suppliers");
			Supplier nintendo = new Supplier("Nintendo", "Redmond", "98052");
			Supplier lenovo = new Supplier("Lenovo", "Morrisville", "27560");
			Supplier keurig = new Supplier("Keurig", "Burlington", "01803");
			supplierRepository.saveAll(List.of(nintendo, lenovo, keurig));
			
			System.out.println("Adding products and wishlist");
			Product nintendoSwitch = new Product("Nintendo Switch", 299.99, nintendo, 50, 25, "Gaming");
			Product singleServeCoffeeMaker = new Product("K-Iced Single Serve Coffee Maker", 99.99, keurig, 100, 50, "Appliances");
			nintendoSwitch.setUPC(generateUPC());
			singleServeCoffeeMaker.setUPC(generateUPC());
			productRepository.saveAll(List.of(nintendoSwitch, singleServeCoffeeMaker));
			
			System.out.println("Adding orders");
			Orders order = new Orders(c1, LocalDate.of(2023, Month.APRIL, 21), PaymentMethod.MC, "0283-4914-2341");
			ordersRepository.save(order);
			
			System.out.println("Adding contents");
			Contents contents1 = new Contents(order, nintendoSwitch, 1);
			Contents contents2 = new Contents(order, singleServeCoffeeMaker, 2);
			contentsRepository.saveAll(List.of(contents1, contents2));
			
			System.out.println("Adding ratings");
			Rating r1 = new Rating(c1, nintendoSwitch, 5, LocalDate.of(2023, Month.APRIL, 22));
			ratingRepository.save(r1);
			
			System.out.println("Adding to wishlist");
			Wish wishItem1 = new Wish(c1, singleServeCoffeeMaker);
			wishRepository.save(wishItem1);
			
		};
	}
	
	private String generateUPC() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return String.valueOf(random.nextLong(10_000_000_000L, 100_000_000_000L));
	}
}

