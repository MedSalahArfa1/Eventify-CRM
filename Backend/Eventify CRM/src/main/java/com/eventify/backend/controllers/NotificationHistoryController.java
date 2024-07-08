package com.eventify.backend.controllers;

import com.eventify.backend.entities.NotificationHistoryEntity;
import com.eventify.backend.services.servicesInter.NotificationHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationHistoryController {
    @Autowired
    private NotificationHistoryServiceInter notificationHistoryService;

    @GetMapping
    public List<NotificationHistoryEntity> getAllNotifications() {
        return notificationHistoryService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationHistoryEntity> getNotificationById(@PathVariable Long id) {
        NotificationHistoryEntity notification = notificationHistoryService.getNotificationById(id);
        return notification != null ? ResponseEntity.ok(notification) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public NotificationHistoryEntity createNotification(@RequestBody NotificationHistoryEntity notification) {
        return notificationHistoryService.createNotification(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationHistoryEntity> updateNotification(@PathVariable Long id, @RequestBody NotificationHistoryEntity notificationDetails) {
        NotificationHistoryEntity updatedNotification = notificationHistoryService.updateNotification(id, notificationDetails);
        return updatedNotification != null ? ResponseEntity.ok(updatedNotification) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationHistoryService.deleteNotification(id);
    }
}
