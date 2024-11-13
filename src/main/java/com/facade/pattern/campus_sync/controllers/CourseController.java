package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.services.academic.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Agregar varios cursos
    @PostMapping("/addMultiple")
    public ResponseEntity<String> saveMultipleCourses(@RequestBody List<Course> courses) {
        courseService.saveMultipleCourses(courses); // Guardar los cursos
        return new ResponseEntity<>("Cursos guardados exitosamente", HttpStatus.CREATED);
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener un curso por código
    @GetMapping("/code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        Optional<Course> course = courseService.getCourseByCode(code);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Guardar un nuevo curso
    @PostMapping
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        courseService.saveCourse(course); // Guardar el curso
        return new ResponseEntity<>("Curso guardado exitosamente", HttpStatus.CREATED);
    }

    // Actualizar un curso
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        if (updatedCourse != null) {
            return new ResponseEntity<>("Curso actualizado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Curso no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        Optional<Course> courseOptional = courseService.getCourseById(id); // Verificar si el curso existe
        if (courseOptional.isPresent()) {
            courseService.deleteCourse(id); // Eliminar el curso
            return new ResponseEntity<>("Curso eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Curso no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
