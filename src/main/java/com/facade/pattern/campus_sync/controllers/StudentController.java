package com.facade.pattern.campus_sync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.facade.pattern.campus_sync.controllers.request.LoginRequest;
import com.facade.pattern.campus_sync.controllers.response.LoginResponse;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.services.auth.StudentService;
import com.facade.pattern.campus_sync.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Obtener todos los estudiantes
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));
    }

    // Crear un nuevo estudiante
    @PostMapping("/add")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante creado con éxito.");
    }

    // Endpoint para agregar múltiples estudiantes
    @PostMapping("/addMultiple")
    public ResponseEntity<String> addMultipleStudents(@RequestBody List<Student> students) {
        for (Student student : students) {
            studentService.addStudent(student);
        }
        return ResponseEntity.ok("All students added successfully.");
    }

    // Actualizar un estudiante
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student updated = studentService.updateStudent(id, updatedStudent);
        if (updated != null) {
            return ResponseEntity.ok("Estudiante actualizado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado con ID: " + id);
        }
    }

    // Eliminar un estudiante
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id); // Cambiado a void, no necesita capturar booleano
            return ResponseEntity.ok("Estudiante eliminado con éxito.");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Estudiante no encontrado con ID: " + id);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Student> student = studentService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (student.isPresent()) {
            // Generar un token único usando UUID
            String token = UUID.randomUUID().toString(); // Genera un token único
            // Crear la respuesta con el token y el estudiante convertido a JSON
            LoginResponse response = new LoginResponse(token, student.get());
            return ResponseEntity.ok(response); // Devuelve 200 OK con el JSON del student y el token
        } else {
            // Si no se encuentra el estudiante, devolvemos el mensaje de error
            LoginResponse errorResponse = new LoginResponse("Credenciales inválidas", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); // Devuelve 401 UNAUTHORIZED con
                                                                                       // el mensaje de error
        }
    }
}
