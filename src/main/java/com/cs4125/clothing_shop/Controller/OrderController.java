package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Command.Command;
import com.cs4125.clothing_shop.Command.PlaceOrderCommand;
import com.cs4125.clothing_shop.Config.ApiResponse;

import com.cs4125.clothing_shop.Model.Order;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.AuthenticationService;
import com.cs4125.clothing_shop.Service.OrderService;
import com.exceptions.AuthenticationFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;




    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token)
            throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // place the order
        Command command = new PlaceOrderCommand(orderService,user);
        command.execute();
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // get orders
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

}