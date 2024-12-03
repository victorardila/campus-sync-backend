package com.facade.pattern.campus_sync.repositories;

import java.util.List;

import com.facade.pattern.campus_sync.domains.Student;

public interface StudentRepository {
    Student save(Student student);

    Student findById(String id);

    void deleteById(String id);

    List<Student> findAll();
}
