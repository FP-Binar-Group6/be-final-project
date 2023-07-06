package com.fpbinar6.code.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpbinar6.code.models.Notification;
import com.fpbinar6.code.models.dto.NotificationRequestDTO;
import com.fpbinar6.code.repository.NotificationRepository;
import com.fpbinar6.code.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationService notificationService, NotificationRepository notificationRepository) {
        this.notificationService = notificationService;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody NotificationRequestDTO requestDTO) {
        notificationService.createNotification(requestDTO.getUserId(), requestDTO.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable int userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}

