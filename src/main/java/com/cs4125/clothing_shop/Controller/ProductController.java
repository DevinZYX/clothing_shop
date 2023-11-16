package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Config.ApiResponse;
import com.cs4125.clothing_shop.Discount.Discount;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Service.BrandService;
import com.cs4125.clothing_shop.Service.CategoryService;
import com.cs4125.clothing_shop.Service.ProductService;
import com.exceptions.ProductNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    // list all the products
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.listProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/discountedPrice")
    public ResponseEntity<Double> getDiscountedPrice(Integer id) throws ProductNotExistException {
        double price = productService.getDiscountedPrice(id);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    // update a product
    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        Optional<Brand> optionalBrand = brandService.readBrand(productDto.getBrandId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        if (!optionalBrand.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "brand is invalid"), HttpStatus.CONFLICT);
        }
        Discount discount = productDto.getDiscount();
        Category category = optionalCategory.get();
        Brand brand = optionalBrand.get();
        productService.updateProduct(productID, productDto, category, brand, discount);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

}
