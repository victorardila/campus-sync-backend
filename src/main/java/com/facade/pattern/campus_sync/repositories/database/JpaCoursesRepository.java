package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facade.pattern.campus_sync.domains.Course;

public interface JpaCoursesRepository extends JpaRepository<Course, String> {

}