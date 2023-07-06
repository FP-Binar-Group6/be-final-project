package com.fpbinar6.code.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Notification;
import com.fpbinar6.code.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(int userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}

