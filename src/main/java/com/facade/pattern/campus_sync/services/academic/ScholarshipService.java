package com.facade.pattern.campus_sync.services.academic;

import com.facade.pattern.campus_sync.domains.Scholarship;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    private List<Scholarship> scholarships = new ArrayList<>();

    // Método para obtener todas las becas
    public List<Scholarship> getAllScholarships() {
        return scholarships;
    }

    // Método para obtener una beca por ID
    public Scholarship getScholarshipById(Long id) {
        return scholarships.stream()
                .filter(scholarship -> scholarship.getId().equals(id))
                .findFirst()
                .orElse(null); // Si no se encuentra, retorna null
    }

    // Método para crear una nueva beca
    public Scholarship createScholarship(Scholarship scholarship) {
        scholarships.add(scholarship);
        return scholarship;
    }

    // Método para actualizar una beca existente
    public Scholarship updateScholarship(Long id, Scholarship scholarshipDetails) {
        Scholarship scholarship = getScholarshipById(id);
        if (scholarship != null) {
            scholarship.setName(scholarshipDetails.getName());
            scholarship.setDescription(scholarshipDetails.getDescription());
            scholarship.setRequirements(scholarshipDetails.getRequirements());
            scholarship.setDiscount(scholarshipDetails.getDiscount());
            return scholarship;
        }
        return null;
    }

    // Método para eliminar una beca
    public boolean deleteScholarship(Long id) {
        Scholarship scholarship = getScholarshipById(id);
        if (scholarship != null) {
            scholarships.remove(scholarship);
            return true;
        }
        return false;
    }

    // Guardar múltiples cursos (en memoria)
    public List<Scholarship> saveMultipleScholarship(List<Scholarship> scholarshipToSave) {
        scholarships.addAll(scholarshipToSave);
        return scholarshipToSave;
    }
}
