package com.codegym.test_socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    // Initialize Notifications
    private TestCount notifications = new TestCount(0);

    @GetMapping("/notify")
    public String getNotification() {

        // Increment Notification by one
        notifications.increment();

        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notifications);

        return "Notifications successfully sent to Angular !";
    }
}
