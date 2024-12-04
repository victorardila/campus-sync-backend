package com.facade.pattern.campus_sync.services.academic;

import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.repositories.ScholarshipRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    @Autowired
    public ScholarshipService(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    // Método para obtener todas las becas
    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    // Obtener una beca por su ID
    public Optional<Scholarship> getScholarshipById(Long id) {
        return Optional.ofNullable(scholarshipRepository.findById(id));
    }

    // Guardar una nueva beca
    public Scholarship saveCourse(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }

    // Actualizar una beca existente
    public boolean updateCourse(Long id, Scholarship scholarshipDetails) {
        Optional<Scholarship> existingSholarship = getScholarshipById(id);
        if (existingSholarship.isPresent()) {
            Scholarship scholarship = existingSholarship.get();
            scholarship.setName(scholarshipDetails.getName());
            scholarship.setDescription((scholarshipDetails.getDescription()));
            scholarship.setRequirements(scholarshipDetails.getRequirements());
            scholarship.setDiscount(scholarshipDetails.getDiscount());
            scholarshipRepository.save(scholarship); // Guardamos los cambios en el repositorio
            return true;
        }
        return false;
    }

    public boolean deleteScholarship(Long id) {
        Optional<Scholarship> existingSholarship = getScholarshipById(id);
        if (existingSholarship.isPresent()) {
            scholarshipRepository.deleteById(id); // Eliminar del repositorio
            return true;
        }
        return false;
    }

    // Guardar múltiples cursos
    public List<Scholarship> saveMultipleCourses(List<Scholarship> scholarshipToSave) {
        return scholarshipRepository.saveAll(scholarshipToSave);
    }
}
