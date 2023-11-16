package com.cs4125.clothing_shop.Discount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class NoDiscountState implements DiscountState{

    private Integer id;
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}
