package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Discount.*;
import com.cs4125.clothing_shop.Dto.Product.ProductDto;
import com.cs4125.clothing_shop.Model.*;
import com.cs4125.clothing_shop.Observer.OrderObserver;
import com.cs4125.clothing_shop.Repository.ProductRepo;
import com.exceptions.ProductNotExistException;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements OrderObserver {
    @Autowired
    private ProductRepo productRepo;


    private final Timer getProductByIdTimer;


    public static Product getProductFromDto(ProductDto productDto, Category category, Brand brand) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setBrand(brand);
        product.setDiscount(productDto.getDiscount());
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
        List<ProductDto> productDto = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDto.add(new ProductDto(product));
        }
        return productDto;
    }

    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category, Brand brand, Discount discount) {
        Product product = getProductFromDto(productDto, category, brand);
        product.setDiscount(discount);
        // set the id for updating
        product.setId(productID);
        // update
        productRepo.save(product);
    }
    public ProductService(MeterRegistry meterRegistry) {
        // Create a timer metric
        this.getProductByIdTimer = meterRegistry.timer("getProductById.time");
    }



    //getProductId
    public Product getProductById(Integer productId){
        return getProductByIdTimer.record(() -> {
            // Actual method implementation
            Optional<Product> optionalProduct = productRepo.findById(productId);
            if (optionalProduct.isEmpty())
                try {
                    throw new ProductNotExistException("Product id is invalid " + productId);
                } catch (ProductNotExistException e) {
                    throw new RuntimeException(e);
                }
            return optionalProduct.get();
        });

    }

    public double getDiscountedPrice(Integer id) throws ProductNotExistException {
        Product product = getProductById(id);
        product.setDiscountState(product.getDiscount());
        DiscountState discountState = product.getDiscountState();
        return discountState.applyDiscount(product.getPrice());
    }


    @Override
    public void onOrderPlaced(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            int newStock = product.getStock() - item.getQuantity();
            product.setStock(newStock);
            // Save the updated product
            productRepo.save(product);
        }
    }
}
