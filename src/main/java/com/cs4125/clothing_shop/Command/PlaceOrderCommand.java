package com.cs4125.clothing_shop.Command;

import com.cs4125.clothing_shop.Model.Order;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.OrderService;

public class PlaceOrderCommand implements Command {
    private OrderService orderService;
    private User user;


    public PlaceOrderCommand(OrderService orderService, User user) {
        this.orderService = orderService;
        this.user = user;
    }

    @Override
    public void execute() {
        orderService.placeOrder(user);
    }
}
