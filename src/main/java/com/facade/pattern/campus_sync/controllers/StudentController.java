package com.facade.pattern.campus_sync.controllers;

import org.hibernate.hql.internal.ast.util.ASTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facade.pattern.campus_sync.controllers.request.LoginRequest;
import com.facade.pattern.campus_sync.controllers.response.LoginResponse;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.services.auth.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController() {
        this.studentService = new StudentService();
    }

    // Obtener todos los estudiantes
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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
    @PutMapping("/upadte/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        boolean updated = studentService.updateStudent(id, updatedStudent);
        if (updated) {
            return ResponseEntity.ok("Estudiante actualizado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado.");
        }
    }

    // Eliminar un estudiante
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok("Estudiante eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Student> student = studentService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (student.isPresent()) {
            // Generar el token
            // String token = ASTUtil.getTokenTypeName(Student.class, 15);
            String[] tokengenate = ASTUtil.generateTokenNameCache(Student.class); // Aquí generamos el token para el
                                                                                  // estudiante
            String token = tokengenate.toString().split(";")[1];
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
