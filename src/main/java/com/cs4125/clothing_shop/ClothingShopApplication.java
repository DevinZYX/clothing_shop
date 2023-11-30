package com.cs4125.clothing_shop;

import com.cs4125.clothing_shop.Discount.Discount;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.BrandService;
import com.cs4125.clothing_shop.Service.CategoryService;
import com.cs4125.clothing_shop.Service.ProductService;
import com.cs4125.clothing_shop.Service.UserService;
import com.exceptions.AuthenticationFailException;
import com.exceptions.CustomException;
import com.exceptions.ProductNotExistException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClothingShopApplication {

	public static void main(String[] args)  {


//		Product product = new Product("name","123",200,"asd", new Category(), new Brand(), Discount.MID_SEASON_DISCOUNT,200);
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
