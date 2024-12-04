package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.repositories.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryCourseRepository implements CourseRepository {
    private Map<Long, Course> courseMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Course save(Course course) {
        if (course.getId() == null) {
            course.setId(currentId++);
        }
        courseMap.put(course.getId(), course);
        return course;
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        for (Course course : courses) {
            save(course);
        }
        return courses;
    }

    @Override
    public Course findById(Long id) {
        return courseMap.get(id);
    }

    @Override
    public void deleteById(Long id) {
        courseMap.remove(id);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }

    @Override
    public Course findByCode(String code) {
        for (Course course : courseMap.values()) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null; // Devuelve null si no encuentra el curso
    }
}
