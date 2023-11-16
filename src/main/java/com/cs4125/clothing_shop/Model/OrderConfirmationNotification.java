package com.cs4125.clothing_shop.Model;

public class OrderConfirmationNotification implements Notification {

    private final String orderDetails;

    public OrderConfirmationNotification(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String getMessage() {
        return "Order Confirmed: " + orderDetails;
    }
}