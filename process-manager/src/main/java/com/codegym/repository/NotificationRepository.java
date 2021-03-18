package com.codegym.repository;

import com.codegym.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Page<Notification> findAllByAccount_IdOrderByTimeNotificationDesc(Integer account_id, Pageable pageable);

    List<Notification> findAllByAccount_IdAndStatusFalse(Integer account_id);
}
