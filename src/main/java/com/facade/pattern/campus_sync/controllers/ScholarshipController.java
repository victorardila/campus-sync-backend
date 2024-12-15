package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.services.academic.ScholarshipService;
import com.facade.pattern.campus_sync.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scholarship")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @Autowired
    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    // Obtener todas las becas
    @GetMapping("/all")
    public ResponseEntity<List<Scholarship>> getAllScholarships() {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        return ResponseEntity.ok(scholarships); // Devuelve 200 OK con la lista de becas
    }

    // Obtener una beca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Scholarship> getScholarshipById(@PathVariable Long id) {
        Optional<Scholarship> scholarship = scholarshipService.getScholarshipById(id);
        return scholarship.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Beca no encontrada con ID: " + id));
    }

    // Crear una nueva beca
    @PostMapping("/add")
    public ResponseEntity<String> saveScholarship(@RequestBody Scholarship scholarship) {
        scholarshipService.saveScholarship(scholarship); // Guardar la beca
        return ResponseEntity.status(HttpStatus.CREATED).body("Beca creada exitosamente."); // Devuelve 201 CREATED
    }

    // Crear varias becas
    @PostMapping("/addMultiple")
    public ResponseEntity<String> saveMultipleScholarships(@RequestBody List<Scholarship> scholarships) {
        for (Scholarship scholarship : scholarships) {
            scholarshipService.saveScholarship(scholarship); // Guardar cada beca individualmente
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Becas creadas exitosamente."); // Devuelve 201 CREATED
    }

    // Actualizar una beca existente
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateScholarship(@PathVariable Long id,
            @RequestBody Scholarship scholarshipDetails) {
        boolean updated = scholarshipService.updateScholarship(id, scholarshipDetails);
        if (updated) {
            return ResponseEntity.ok("Beca actualizada exitosamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Beca no encontrada con ID: " + id);
        }
    }

    // Eliminar una beca
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScholarship(@PathVariable Long id) {
        boolean deleted = scholarshipService.deleteScholarship(id);
        if (deleted) {
            return ResponseEntity.ok("Beca eliminada exitosamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Beca no encontrada con ID: " + id);
        }
    }
}
