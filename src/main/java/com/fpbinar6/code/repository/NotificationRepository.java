package com.fpbinar6.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpbinar6.code.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.userId = :userId ORDER BY n.createdAt DESC")
    List<Notification> findByUserId(int userId);
    
}
