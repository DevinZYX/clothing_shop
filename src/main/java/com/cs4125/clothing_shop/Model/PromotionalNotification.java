package com.cs4125.clothing_shop.Model;

public class PromotionalNotification implements Notification {

    private final String promotionDetails;

    public PromotionalNotification(String promotionDetails) {
        this.promotionDetails = promotionDetails;
    }

    @Override
    public String getMessage() {
        return "Special Promotion: " + promotionDetails;
    }
}