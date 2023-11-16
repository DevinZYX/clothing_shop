package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Factory.NotificationFactory;
import com.cs4125.clothing_shop.Factory.NotificationType;
import com.cs4125.clothing_shop.Model.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(NotificationType type, String details) {
        Notification notification = NotificationFactory.createNotification(type, details);
        // Code to send the notification (e.g., email, SMS)
    }
}