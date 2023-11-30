package com.cs4125.clothing_shop.Command;

import com.cs4125.clothing_shop.Discount.Discount;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Service.ProductService;

public class updateProductCommand implements Command{
    ProductService productService;
    Integer id;
    ProductDto productDto;
    Category category;
    Brand brand;
    Discount discount;

    public updateProductCommand(ProductService productService, Integer id, ProductDto productDto, Category category, Brand brand, Discount discount){
        this.productService = productService;
        this.id = id;
        this.productDto = productDto;
        this.category = category;
        this.brand = brand;
        this.discount = discount;
    }



    @Override
    public void execute() {
        productService.updateProduct(id,productDto,category,brand,discount);
    }
}
