package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Config.ApiResponse;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Service.BrandService;
import com.cs4125.clothing_shop.Service.CategoryService;
import com.cs4125.clothing_shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        Optional<Brand> optionalBrand = brandService.readBrand(productDto.getBrandId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        Brand brand = optionalBrand.get();
        productService.addProduct(productDto, category, brand);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }
}
