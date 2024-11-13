package com.facade.pattern.campus_sync.controllers;


import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.services.academic.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarship")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    // Obtener todas las becas
    @GetMapping("/all")
    public List<Scholarship> getAllScholarships() {
        return scholarshipService.getAllScholarships();
    }

    // Obtener una beca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Scholarship> getScholarshipById(@PathVariable Long id) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        if (scholarship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scholarship, HttpStatus.OK);
    }

    /// Agregar varios cursos
    @PostMapping("/addMultiple")
    public ResponseEntity<String> saveMultipleScholarship(@RequestBody List<Scholarship> scholarships) {
        List<Scholarship> savedScholarships = scholarshipService.saveMultipleScholarship(scholarships);
        return new ResponseEntity<>("Se han agregado " + savedScholarships.size() + " becas exitosamente",
                HttpStatus.CREATED);
    }

    // Crear una nueva beca
    @PostMapping("/add")
    public ResponseEntity<String> createScholarship(@RequestBody Scholarship scholarship) {
        
        Scholarship createdScholarship = scholarshipService.createScholarship(scholarship);
        return new ResponseEntity<>("Beca creada exitosamente", HttpStatus.CREATED);
    }

    // Actualizar una beca existente
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateScholarship(@PathVariable Long id,
            @RequestBody Scholarship scholarshipDetails) {
        Scholarship updatedScholarship = scholarshipService.updateScholarship(id, scholarshipDetails);
        if (updatedScholarship == null) {
            return new ResponseEntity<>("Beca no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Beca actualizada exitosamente", HttpStatus.OK);
    }

    // Eliminar una beca
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScholarship(@PathVariable Long id) {
        boolean isDeleted = scholarshipService.deleteScholarship(id);
        if (!isDeleted) {
            return new ResponseEntity<>("Beca no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Beca eliminada exitosamente", HttpStatus.OK);
    }

}


