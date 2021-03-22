package com.codegym.service;

import com.codegym.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    void save(Notification notification);

    Page<Notification> getListNotification(Integer id, Pageable pageable);

    List<Notification> getListNotificationNotSeen(Integer id);
}
