package com.cs4125.clothing_shop.Command;

import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Service.ProductService;

public class addProductCommand implements Command {

    ProductService productService;

    ProductDto productDto;
    Category category;
    Brand brand;

    public addProductCommand(ProductService productService, ProductDto productDto, Category category, Brand brand){
        this.productService = productService;
        this.productDto = productDto;
        this.category = category;
        this.brand = brand;
    }
    @Override
    public void execute() {
        productService.addProduct(productDto,category,brand);
    }
}
