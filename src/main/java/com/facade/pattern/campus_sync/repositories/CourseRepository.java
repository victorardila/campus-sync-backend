package com.facade.pattern.campus_sync.repositories;

import java.util.List;

import com.facade.pattern.campus_sync.domains.Course;

public interface CourseRepository {

    Course save(Course course); // Guardar un curso

    List<Course> saveAll(List<Course> courses); // Guardar múltiples cursos

    Course findById(Long id); // Buscar un curso por ID

    void deleteById(Long id); // Eliminar un curso por ID

    List<Course> findAll(); // Obtener todos los cursos

    Course findByCode(String code); // Buscar un curso por su código
}
