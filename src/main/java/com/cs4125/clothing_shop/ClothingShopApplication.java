package com.cs4125.clothing_shop;

import com.cs4125.clothing_shop.Discount.Discount;
import com.cs4125.clothing_shop.Discount.DiscountState;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Repository.ProductRepo;
import com.cs4125.clothing_shop.Service.ProductService;
import com.exceptions.ProductNotExistException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClothingShopApplication {

	public static void main(String[] args) throws ProductNotExistException {


//		Product product = new Product("name","123",200,"asd", new Category(), new Brand(), Discount.MID_SEASON_DISCOUNT);
//
//		ProductDto productDto = new ProductDto(product);
//
//		DiscountState discountState = product.getDiscountState();
//		double price = discountState.applyDiscount(product.getPrice());
//
//		System.out.println(price);

		SpringApplication.run(ClothingShopApplication.class, args);

	}

}
