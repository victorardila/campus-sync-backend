package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.services.academic.CourseService;
import com.facade.pattern.campus_sync.exception.ResourceNotFoundException; // Asegúrate de tener esta clase
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Obtener todos los cursos
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses); // Devuelve 200 OK con la lista de cursos
    }

    // Obtener un curso por código
    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        Optional<Course> course = courseService.getCourseByCode(code);
        return course.map(value -> ResponseEntity.ok(value))
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con código: " + code));
    }

    // Guardar un nuevo curso
    @PostMapping("/add")
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        courseService.saveCourse(course); // Guardar el curso
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso guardado exitosamente."); // Devuelve 201 CREATED
    }

    // Agregar varios cursos
    @PostMapping("/addMultiple")
    public ResponseEntity<String> saveMultipleCourses(@RequestBody List<Course> courses) {
        for (Course course : courses) {
            courseService.saveCourse(course); // Guardar cada curso individualmente
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Cursos guardados exitosamente."); // Devuelve 201 CREATED
    }

    // Actualizar un curso
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        boolean updated = courseService.updateCourse(id, courseDetails);
        if (updated) {
            return ResponseEntity.ok("Curso actualizado exitosamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Curso no encontrado con ID: " + id);
        }
    }

    // Eliminar un curso
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.ok("Curso eliminado exitosamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Curso no encontrado con ID: " + id);
        }
    }
}
