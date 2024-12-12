package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    @Override
    public Student save(Student student) {
        // Verifica si el estudiante ya existe
        Optional<Student> existingStudent = findById(student.getId());
        if (existingStudent.isPresent()) {
            // Actualiza el estudiante existente
            students.remove(existingStudent.get());
        }
        // Agrega el nuevo o actualizado estudiante
        students.add(student);
        return student; // Devuelve el objeto guardado
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(students::remove);
    }
}
