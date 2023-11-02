package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;


    public static Product getProductFromDto(ProductDto productDto, Category category, Brand brand) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setBrand(brand);
        return product;
    }


    public void addProduct(ProductDto productDto, Category category, Brand brand) {
        Product product = getProductFromDto(productDto, category, brand);
        productRepo.save(product);
    }

    // list of all the products
    public List<ProductDto> listProducts() {
        // first fetch all the products
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category, Brand brand) {
        Product product = getProductFromDto(productDto, category, brand);
        // set the id for updating
        product.setId(productID);
        // update
        productRepo.save(product);
    }


}
