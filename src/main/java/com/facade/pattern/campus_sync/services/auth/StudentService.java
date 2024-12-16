package com.facade.pattern.campus_sync.services.auth;

import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getUsername().equals(username))
                .findFirst();
    }

    // buscar por id
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();

            // Actualiza los campos necesarios
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAcademicProgram(updatedStudent.getAcademicProgram());
            existingStudent.setMoney(updatedStudent.getMoney()); // Actualiza el dinero del estudiante

            // Guarda los cambios
            return studentRepository.save(existingStudent); // Devuelve el estudiante actualizado
        }
        return null; // O lanza una excepción si no se encuentra
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id); // Llama al nuevo método
    }

    public Optional<Student> authenticate(String username, String password) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getUsername().equals(username) && student.getPassword().equals(password))
                .findFirst();
    }
}
