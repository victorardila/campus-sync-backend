package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facade.pattern.campus_sync.domains.Student;

public interface JpaStudentRepository extends JpaRepository<Student, String> {
    // This is a JPA repository interface for Student entity
}
