package com.cs4125.clothing_shop.Dto.Cart;

import javax.validation.constraints.NotNull;

import com.cs4125.clothing_shop.Model.Cart;
import com.cs4125.clothing_shop.Model.Product;

public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id =id;
    } 

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product prodcut){
        this.product = prodcut;
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}