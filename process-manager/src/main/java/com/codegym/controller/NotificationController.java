package com.codegym.controller;

import com.codegym.entity.Notification;
import com.codegym.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    private ResponseEntity<?> getListNotification(@PathVariable Integer id,
                                                  @PageableDefault(size = 5) Pageable pageable) {
        Page<Notification> notificationList = notificationService.getListNotification(id, pageable);

        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

    @GetMapping("/seen-notification/{id}")
    private ResponseEntity<?> seenNotification(@PathVariable Integer id,
                                               @PageableDefault(size = 5) Pageable pageable) {
        List<Notification> notificationList = notificationService.getListNotificationNotSeen(id);

        for (Notification notification : notificationList) {
            notification.setStatus(true);
            notificationService.save(notification);
        }

        Page<Notification> notificationPage = notificationService.getListNotification(id, pageable);

        return new ResponseEntity<>(notificationPage, HttpStatus.OK);
    }
}
