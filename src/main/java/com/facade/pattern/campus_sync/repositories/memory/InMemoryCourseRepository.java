package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.repositories.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course save(Course course) {
        courses.removeIf(c -> c.getId().equals(course.getId())); // Elimina el curso existente si lo hay
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst(); // Cambia a Optional
    }

    @Override
    public void deleteById(Long id) {
        courses.removeIf(course -> course.getId().equals(id));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Course findByCode(String code) {
        return courses.stream()
                .filter(course -> course.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
