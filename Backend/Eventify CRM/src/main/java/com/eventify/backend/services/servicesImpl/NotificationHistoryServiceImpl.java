package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.NotificationHistoryEntity;
import com.eventify.backend.repositories.NotificationHistoryRepository;
import com.eventify.backend.services.servicesInter.NotificationHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationHistoryServiceImpl implements NotificationHistoryServiceInter {

    @Autowired
    private NotificationHistoryRepository notificationHistoryRepository;

    @Override
    public List<NotificationHistoryEntity> getAllNotifications() {
        return notificationHistoryRepository.findAll();
    }

    @Override
    public NotificationHistoryEntity getNotificationById(Long id) {
        return notificationHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public NotificationHistoryEntity createNotification(NotificationHistoryEntity notification) {
        return notificationHistoryRepository.save(notification);
    }

    @Override
    public NotificationHistoryEntity updateNotification(Long id, NotificationHistoryEntity notificationDetails) {
        NotificationHistoryEntity notification = notificationHistoryRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setMessage(notificationDetails.getMessage());
            notification.setDateTime(notificationDetails.getDateTime());
            notification.setType(notificationDetails.getType());
            notification.setUser(notificationDetails.getUser());
            return notificationHistoryRepository.save(notification);
        }
        return null;
    }

    @Override
    public void deleteNotification(Long id) {
        notificationHistoryRepository.deleteById(id);
    }
}
