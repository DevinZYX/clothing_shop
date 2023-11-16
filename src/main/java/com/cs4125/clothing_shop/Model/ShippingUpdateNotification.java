package com.cs4125.clothing_shop.Model;

public class ShippingUpdateNotification implements Notification {

    private final String trackingNumber;

    public ShippingUpdateNotification(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String getMessage() {
        return "Your order has been shipped. Tracking Number: " + trackingNumber;
    }
}