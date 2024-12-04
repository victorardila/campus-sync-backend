package com.facade.pattern.campus_sync.repositories;

import java.util.List;
import java.util.Optional;

import com.facade.pattern.campus_sync.domains.Notification;

public interface NotificationRepository {
    Notification save(Notification notification); // Guardar un curso

    List<Notification> findAll(); // Obtener todos los cursos

    Optional<Notification> findById(Long id); // Buscar un curso por ID

    void deleteById(Long id); // Eliminar un curso por ID

    List<Notification> saveAll(List<Notification> notification); // Guardar múltiples cursos
}
