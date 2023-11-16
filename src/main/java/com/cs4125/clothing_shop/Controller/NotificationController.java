package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Factory.NotificationType;
import com.cs4125.clothing_shop.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam NotificationType type, @RequestParam String details) {
        notificationService.sendNotification(type, details);
        return "Notification sent successfully";
    }
}