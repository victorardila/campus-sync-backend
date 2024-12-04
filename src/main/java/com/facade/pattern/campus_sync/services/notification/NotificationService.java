package com.facade.pattern.campus_sync.services.notification;

import com.facade.pattern.campus_sync.domains.Notification;
import com.facade.pattern.campus_sync.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Enviar una notificación de confirmación de matrícula
    public Notification sendConfirmationNotification(String title, String message) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType("success");
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);

        return notificationRepository.save(notification); // Guardar la notificación en la base de datos
    }

    // Método para enviar una notificación personalizada
    public Notification sendCustomNotification(String title, String message, String type) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);

        return notificationRepository.save(notification); // Guardar la notificación en la base de datos
    }

    // Actualiza el estado de una notificación a "leída"
    public boolean markAsRead(Long notificationId) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);

        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setRead(true);
            notificationRepository.save(notification); // Guardar los cambios en la base de datos
            return true;
        }
        return false; // Si la notificación no existe, retorna false
    }

    // Método para obtener todas las notificaciones
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll(); // Retorna todas las notificaciones desde la base de datos
    }

    // Método para obtener una notificación por ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id); // Buscar la notificación por ID en la base de datos
    }
}
