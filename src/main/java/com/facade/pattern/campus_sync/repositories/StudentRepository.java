package com.facade.pattern.campus_sync.repositories;

import com.facade.pattern.campus_sync.domains.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student); // Este método se usará para guardar y actualizar

    void deleteById(Long id); // Cambiado a void y renombrado a deleteById
}
