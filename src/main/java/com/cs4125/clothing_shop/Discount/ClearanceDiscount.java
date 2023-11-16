package com.cs4125.clothing_shop.Discount;

public class ClearanceDiscount implements DiscountState{
    @Override
    public double applyDiscount(double price) {
        return price * 0.5;
    }
}
