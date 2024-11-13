package com.facade.pattern.campus_sync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.pattern.EnrollmentFacade;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentFacade enrollmentFacade;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return enrollmentFacade.getAvailableCourses(); // Paso 1: Selección de cursos
    }

    @GetMapping("/scholarships")
    public List<Scholarship> getScholarships() {
        return enrollmentFacade.getAvailableScholarships(); // Paso 2: Selección de becas
    }

    @PostMapping("/generateInvoice")
    public Invoice generateInvoice(@RequestBody Student student, @RequestBody List<Course> courses,
            @RequestBody Scholarship scholarship) {
        return enrollmentFacade.generateInvoice(student, courses, scholarship); // Paso 3: Generación de factura
    }

    @PostMapping("/processPayment")
    public String processPayment(@RequestParam String paymentMethod, @RequestParam double amount) {
        boolean paymentSuccess = enrollmentFacade.processPayment(paymentMethod, amount); // Paso 4: Proceso de pago
        return paymentSuccess ? "Pago procesado correctamente" : "Error en el pago";
    }

    @PostMapping("/confirmEnrollment")
    public String confirmEnrollment() {
        return enrollmentFacade.confirmEnrollment(); // Paso 5: Confirmación de matrícula
    }
}
