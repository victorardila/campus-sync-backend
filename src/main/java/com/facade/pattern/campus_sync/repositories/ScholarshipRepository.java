package com.facade.pattern.campus_sync.repositories;

import java.util.List;

import com.facade.pattern.campus_sync.domains.Scholarship;

public interface ScholarshipRepository {
    Scholarship save(Scholarship scholarship); // Guardar un curso

    List<Scholarship> findAll(); // Obtener todos los cursos

    Scholarship findById(Long id); // Buscar un curso por ID

    void deleteById(Long id); // Eliminar un curso por ID

    List<Scholarship> saveAll(List<Scholarship> scholarship); // Guardar múltiples cursos
}
