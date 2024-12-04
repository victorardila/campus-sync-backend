package com.facade.pattern.campus_sync.repositories;

import java.util.List;

import com.facade.pattern.campus_sync.domains.Invoice;

public interface InvoiceRepository {
    Invoice save(Invoice invoice); // Guardar un curso

    List<Invoice> findAll(); // Obtener todos los cursos

    Invoice findById(Long id); // Buscar un curso por ID

    void deleteById(Long id); // Eliminar un curso por ID

    List<Invoice> saveAll(List<Invoice> invoice); // Guardar múltiples cursos
}
