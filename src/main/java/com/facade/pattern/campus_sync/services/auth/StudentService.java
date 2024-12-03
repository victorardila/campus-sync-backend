package com.facade.pattern.campus_sync.services.auth;

import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    // Repositorio de estudiantes (puede ser en memoria o JPA, según el perfil)
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(studentRepository.findById(id));
    }

    // Añadir un nuevo estudiante
    public void addStudent(Student student) {
        studentRepository.save(student);
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
            studentRepository.save(student); // Guardamos los cambios en el repositorio
            return true;
        }
        return false;
    }

    // Eliminar estudiante
    public boolean deleteStudent(String id) {
        Optional<Student> existingStudent = getStudentById(id);
        if (existingStudent.isPresent()) {
            studentRepository.deleteById(id); // Eliminar del repositorio
            return true;
        }
        return false;
    }

    // Método para autenticar a un estudiante
    public Optional<Student> authenticate(String username, String password) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getUsername().equals(username) && student.getPassword().equals(password))
                .findFirst();
    }
}
