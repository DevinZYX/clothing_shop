package com.cs4125.clothing_shop.Controller;


import com.cs4125.clothing_shop.Service.CategoryService;
import com.cs4125.clothing_shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//cary
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    //we will need to implement authentication
}
