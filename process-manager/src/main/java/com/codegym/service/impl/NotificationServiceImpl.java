package com.codegym.service.impl;

import com.codegym.entity.Notification;
import com.codegym.repository.NotificationRepository;
import com.codegym.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public Page<Notification> getListNotification(Integer id, Pageable pageable) {
        return notificationRepository.findAllByAccount_IdOrderByTimeNotificationDesc(id,pageable);
    }

    @Override
    public List<Notification> getListNotificationNotSeen(Integer id) {
        return notificationRepository.findAllByAccount_IdAndStatusFalse(id);
    }
}
