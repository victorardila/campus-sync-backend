package com.facade.pattern.campus_sync.repositories;

import java.util.List;
import java.util.Optional;

import com.facade.pattern.campus_sync.domains.Scholarship;

public interface ScholarshipRepository {
    Scholarship save(Scholarship scholarship); // Guardar un curso

    List<Scholarship> findAll(); // Obtener todos los cursos

    Optional<Scholarship> findById(Long id); // Buscar un curso por ID

    void deleteById(Long id); // Eliminar un curso por ID
}
