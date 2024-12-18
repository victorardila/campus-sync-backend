package com.facade.pattern.campus_sync.repositories.database;

import com.facade.pattern.campus_sync.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facade.pattern.campus_sync.repositories.StudentRepository;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, String>, StudentRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
