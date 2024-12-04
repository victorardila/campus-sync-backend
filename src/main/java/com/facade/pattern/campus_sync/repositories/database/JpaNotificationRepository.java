package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facade.pattern.campus_sync.domains.Notification;

public interface JpaNotificationRepository extends JpaRepository<Notification, String> {
    // JpaRepository proporciona todos los métodos necesarios
}
