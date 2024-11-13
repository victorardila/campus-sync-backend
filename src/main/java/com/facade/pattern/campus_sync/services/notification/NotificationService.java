package com.facade.pattern.campus_sync.services.notification;

import com.facade.pattern.campus_sync.domains.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    // Lista en memoria para almacenar las notificaciones
    private List<Notification> notificationList = new ArrayList<>();
    private Long currentId = 1L; // ID para las notificaciones, se incrementa para cada nueva notificación

    // Envía una notificación de confirmación de matrícula
    public void sendConfirmationNotification(String title, String message) {
        Notification notification = new Notification();
        notification.setId(currentId++); // Asignar un ID único a la notificación
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType("success");
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);

        notificationList.add(notification); // Guardar la notificación en la lista en memoria
    }

    // Método para enviar una notificación personalizada
    public void sendCustomNotification(String title, String message, String type) {
        Notification notification = new Notification();
        notification.setId(currentId++); // Asignar un ID único a la notificación
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);

        notificationList.add(notification); // Guardar la notificación en la lista en memoria
    }

    // Actualiza el estado de una notificación a "leída"
    public void markAsRead(Long notificationId) {
        Optional<Notification> notificationOptional = notificationList.stream()
                .filter(notification -> notification.getId().equals(notificationId))
                .findFirst();

        notificationOptional.ifPresent(notification -> {
            notification.setRead(true);
            // No es necesario guardar, ya está en memoria actualizado
        });
    }

    // Método para obtener todas las notificaciones
    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notificationList); // Retornar una copia de las notificaciones
    }

    // Método para obtener una notificación por ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationList.stream().filter(notification -> notification.getId().equals(id)).findFirst();
    }
}
