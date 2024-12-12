package com.facade.pattern.campus_sync.repositories;

import java.util.List;
import java.util.Optional;

import com.facade.pattern.campus_sync.domains.Payment;

public interface PaymentRepository {
    Payment save(Payment payment); // Guardar un curso

    List<Payment> findAll(); // Obtener todos los cursos

    Optional<Payment> findById(Long id); // Buscar un curso por ID

    Optional<Payment> findByTransactionId(String id);

    void deleteById(Long id); // Eliminar un curso por ID
}
