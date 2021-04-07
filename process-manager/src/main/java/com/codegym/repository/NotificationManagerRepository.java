package com.codegym.repository;

import com.codegym.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface NotificationManagerRepository extends JpaRepository<Notification, Integer> {

    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`notification` " +
            "(`content`, `status`, `time_notification`, `title`, `account_id`, `account_send_notification_id`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6) ", nativeQuery = true)
    void createNotification(String content, Boolean status, String timeNotification, String title, Integer accountId, Integer accountSendNotificationId);
    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`notification` " +
            "(`content`, `status`, `time_notification`, `title`, `account_id`, `account_send_notification_id`, `url`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7) ", nativeQuery = true)
    void createNotificationUrl(String content, Boolean status, String timeNotification, String title, Integer accountId, Integer accountSendNotificationId, String url);

    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`notification` " +
            "(`content`, `status`, `time_notification`, `title`, `account_id`, `url`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6) ", nativeQuery = true)
    void createNotificationList(String content, Boolean status, String timeNotification, String title, Integer accountId, String url);

}
