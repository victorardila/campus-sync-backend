package com.facade.pattern.campus_sync.services.academic;

import com.facade.pattern.campus_sync.domains.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Course> courses = new ArrayList<>();

    // Obtener todos los cursos (en memoria)
    public List<Course> getAllCourses() {
        return courses;
    }

    // Obtener un curso por su ID (en memoria)
    public Optional<Course> getCourseById(Long id) {
        return courses.stream().filter(course -> course.getId().equals(id)).findFirst();
    }

    // Obtener un curso por su código (en memoria)
    public Optional<Course> getCourseByCode(String code) {
        return courses.stream().filter(course -> course.getCode().equals(code)).findFirst();
    }

    // Guardar un nuevo curso (en memoria)
    public Course saveCourse(Course course) {
        courses.add(course);
        return course;
    }

    // Actualizar un curso existente (en memoria)
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> courseOptional = getCourseById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setCode(courseDetails.getCode());
            course.setName(courseDetails.getName());
            course.setCredits(courseDetails.getCredits());
            course.setTeacher(courseDetails.getTeacher());
            course.setCurrentQuantity(courseDetails.getCurrentQuantity());
            course.setMaximumLimit(courseDetails.getMaximumLimit());
            course.setSchedule(courseDetails.getSchedule());
            return course;
        } else {
            return null; // Si el curso no se encuentra, devuelve null o lanza una excepción
                         // personalizada
        }
    }

    // Eliminar un curso por su ID (en memoria)
    public void deleteCourse(Long id) {
        courses.removeIf(course -> course.getId().equals(id));
    }

    // Guardar múltiples cursos (en memoria)
    public List<Course> saveMultipleCourses(List<Course> coursesToSave) {
        courses.addAll(coursesToSave);
        return coursesToSave;
    }
}
