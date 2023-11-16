package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Dto.Cart.AddToCartDto;
import com.cs4125.clothing_shop.Dto.Cart.CartDto;
import com.cs4125.clothing_shop.Dto.Cart.CartItemDto;
import com.cs4125.clothing_shop.Model.Cart;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Repository.CartRepo;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        // first get all the cart items for user
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        // convert cart to cartItemDto
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        // calculate the total price
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        // return cart DTO
        return new CartDto(cartItems,totalCost);
    }
}
