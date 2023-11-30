package com.cs4125.clothing_shop.Command;

import com.cs4125.clothing_shop.Dto.Cart.AddToCartDto;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.CartService;


public class addToCartCommand implements Command {
    CartService cartService;
    AddToCartDto addToCartDto;

    Product product;

    User user;

    public addToCartCommand(CartService cartService, AddToCartDto addToCartDto, Product product, User user){
        this.cartService = cartService;
        this.addToCartDto = addToCartDto;
        this.product = product;
        this.user = user;
    }
    @Override
    public void execute() {
        cartService.addToCart(addToCartDto, product, user);
    }
}
