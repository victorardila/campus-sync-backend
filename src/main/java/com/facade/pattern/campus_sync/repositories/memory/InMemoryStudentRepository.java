package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryStudentRepository implements StudentRepository {

    private Map<String, Student> studentMap = new HashMap<>();

    @Override
    public Student save(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findById(String id) {
        return studentMap.get(id);
    }

    @Override
    public void deleteById(String id) {
        studentMap.remove(id); // Elimina el estudiante del mapa por su ID
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values()); // Retorna todos los estudiantes almacenados
    }
}
