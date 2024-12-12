package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.repositories.CourseRepository;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long>, CourseRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
