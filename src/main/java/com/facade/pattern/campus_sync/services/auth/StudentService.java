package com.facade.pattern.campus_sync.services.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.facade.pattern.campus_sync.domains.Student;

public class StudentService {
    // Lista en memoria para almacenar estudiantes
    private static List<Student> students = new ArrayList<>();

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return students;
    }

    // Obtener un estudiante por ID
    public Optional<Student> getStudentById(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    // Añadir un nuevo estudiante
    public void addStudent(Student student) {
        students.add(student);
    }

    // Actualizar estudiante
    public boolean updateStudent(String id, Student updatedStudent) {
        Optional<Student> existingStudent = getStudentById(id);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setAcademicProgram(updatedStudent.getAcademicProgram());
            student.setTipoDescuento(updatedStudent.getTipoDescuento());
            student.setSaldoPagar(updatedStudent.getSaldoPagar());
            student.setCreditosAcumulados(updatedStudent.getCreditosAcumulados());
            student.setMoney(updatedStudent.getMoney());
            return true;
        }
        return false;
    }

    // Eliminar estudiante
    public boolean deleteStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    // Método para autenticar a un estudiante
    public Optional<Student> authenticate(String username, String password) {
        return students.stream()
                .filter(student -> student.getUsername().equals(username) && student.getPassword().equals(password))
                .findFirst();
    }
}
