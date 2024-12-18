package com.facade.pattern.campus_sync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.controllers.request.*;
import com.facade.pattern.campus_sync.pattern.EnrollmentFacade;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentFacade enrollmentFacade;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = enrollmentFacade.getAvailableCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courses);
        }
        return ResponseEntity.ok(courses); // Retorna 200 OK con la lista de cursos
    }

    @GetMapping("/scholarships")
    public ResponseEntity<List<Scholarship>> getScholarships() {
        List<Scholarship> scholarships = enrollmentFacade.getAvailableScholarships();
        if (scholarships.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scholarships);
        }
        return ResponseEntity.ok(scholarships); // Retorna 200 OK con la lista de becas
    }

    @GetMapping("/credit-cost")
    public ResponseEntity<Double> getCreditCost() {
        // Accede al valor de CREDIT_COST desde EnrollmentFacade
        double creditCost = enrollmentFacade.getCreditCost();
        return ResponseEntity.ok(creditCost); // Retorna 200 OK con el costo del crédito
    }

    @PostMapping("/generate-invoice")
    public ResponseEntity<?> generateInvoice(@Validated @RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        try {
            Student student = invoiceRequestDTO.getStudent();
            List<Course> courses = invoiceRequestDTO.getCourses();
            Scholarship scholarship = invoiceRequestDTO.getScholarship();
            // Impresión de los datos recibidos
            Invoice invoice = enrollmentFacade.generateInvoice(student, courses, scholarship);
            return ResponseEntity.status(HttpStatus.CREATED).body(invoice); // Retorna 201 Created con la factura
                                                                            // generada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al generar la factura: " + e.getMessage());
        }
    }

    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(@RequestBody ProcessPaymentRequest processPaymentRequest) {
        Long studentId = processPaymentRequest.getStudentId();
        Payment payment = processPaymentRequest.getPayment();

        boolean paymentSuccess = enrollmentFacade.processPayment(payment, studentId);
        if (paymentSuccess) {
            return ResponseEntity.ok("Pago procesado correctamente. ID de transacción: " + payment.getTransactionId());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el pago");
        }
    }

    @GetMapping("/confirm-enrollment")
    public ResponseEntity<String> confirmEnrollment() {
        String confirmationMessage = enrollmentFacade.confirmEnrollment();
        if (confirmationMessage != null && !confirmationMessage.isEmpty()) {
            return ResponseEntity.ok(confirmationMessage); // Retorna 200 OK si la confirmación es exitosa
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la confirmación de la matrícula"); // Retorna 500 si falla la confirmación
        }
    }
}
