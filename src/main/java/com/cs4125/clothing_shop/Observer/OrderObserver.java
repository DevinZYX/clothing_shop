package com.cs4125.clothing_shop.Observer;

import com.cs4125.clothing_shop.Model.Order;

public interface OrderObserver {
    void onOrderPlaced(Order order);
}