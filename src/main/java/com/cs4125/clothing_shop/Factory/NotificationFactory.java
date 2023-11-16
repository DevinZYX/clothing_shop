package com.cs4125.clothing_shop.Factory;

import com.cs4125.clothing_shop.Model.Notification;
import com.cs4125.clothing_shop.Model.OrderConfirmationNotification;
import com.cs4125.clothing_shop.Model.PromotionalNotification;
import com.cs4125.clothing_shop.Model.ShippingUpdateNotification;

public class NotificationFactory {


    public static Notification createNotification(NotificationType type, String details) {
        switch (type) {
            case ORDER_CONFIRMATION:
                return new OrderConfirmationNotification(details);
            case SHIPPING_UPDATE:
                return new ShippingUpdateNotification(details);
            case PROMOTIONAL:
                return new PromotionalNotification(details);
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}