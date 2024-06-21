package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.NotificationHistoryEntity;

import java.util.List;

public interface NotificationHistoryServiceInter {
    List<NotificationHistoryEntity> getAllNotifications();

    NotificationHistoryEntity getNotificationById(Long id);

    NotificationHistoryEntity createNotification(NotificationHistoryEntity notification);

    NotificationHistoryEntity updateNotification(Long id, NotificationHistoryEntity notificationDetails);

    void deleteNotification(Long id);
}
