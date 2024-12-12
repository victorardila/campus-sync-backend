package com.facade.pattern.campus_sync.services.academic;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Obtener todos los cursos
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Obtener un curso por su ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Obtener un curso por su código
    public Optional<Course> getCourseByCode(String code) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getCode().equals(code))
                .findFirst();
    }

    // Guardar un nuevo curso
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Actualizar un curso existente
    public boolean updateCourse(Long id, Course courseDetails) {
        Optional<Course> existingCourse = getCourseById(id);
        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setCode(courseDetails.getCode());
            course.setName(courseDetails.getName());
            course.setCredits(courseDetails.getCredits());
            course.setTeacher(courseDetails.getTeacher());
            course.setCurrentQuantity(courseDetails.getCurrentQuantity());
            course.setMaximumLimit(courseDetails.getMaximumLimit());
            course.setSchedule(courseDetails.getSchedule());
            courseRepository.save(course); // Guardamos los cambios en el repositorio
            return true;
        }
        return false;
    }

    // Eliminar un curso por su ID
    public boolean deleteCourse(Long id) {
        Optional<Course> existingCourse = getCourseById(id);
        if (existingCourse.isPresent()) {
            courseRepository.deleteById(id); // Eliminar del repositorio
            return true;
        }
        return false;
    }
}
