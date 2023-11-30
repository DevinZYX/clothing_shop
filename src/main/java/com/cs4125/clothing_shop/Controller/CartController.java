package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Command.Command;
import com.cs4125.clothing_shop.Command.addToCartCommand;
import com.cs4125.clothing_shop.Service.ProductService;
import com.exceptions.AuthenticationFailException;
import com.exceptions.ProductNotExistException;
import com.cs4125.clothing_shop.Config.ApiResponse;
import com.cs4125.clothing_shop.Dto.Cart.AddToCartDto;
import com.cs4125.clothing_shop.Dto.Cart.CartDto;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.AuthenticationService;
import com.cs4125.clothing_shop.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//cary
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token)
            throws ProductNotExistException, AuthenticationFailException {
        // first authenticate the token
        authenticationService.authenticate(token);

        // get the user
        User user = authenticationService.getUser(token);

        // find the product to add and add item by service
        Product product = productService.getProductById(addToCartDto.getProductId());
        Command command = new addToCartCommand(cartService,addToCartDto,product,user);
        command.execute();

        // return response
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        // first authenticate the token
        authenticationService.authenticate(token);

        // get the user
        User user = authenticationService.getUser(token);

        // get items in the cart for the user.
        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

}
