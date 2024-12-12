package com.facade.pattern.campus_sync.repositories;

import java.util.List;
import java.util.Optional;

import com.facade.pattern.campus_sync.domains.Invoice;

public interface InvoiceRepository {
    Invoice save(Invoice invoice); // Guardar un curso

    List<Invoice> findAll(); // Obtener todos los cursos

    Optional<Invoice> findById(Long id);

    void deleteById(Long id); // Eliminar un curso por ID
}
