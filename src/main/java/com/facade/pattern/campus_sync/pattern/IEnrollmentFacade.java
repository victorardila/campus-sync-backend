package com.facade.pattern.campus_sync.pattern;

import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Student;

import java.util.List;

public interface IEnrollmentFacade {
    List<Course> getAvailableCourses(); // Paso 1: Obtener cursos disponibles

    List<Scholarship> getAvailableScholarships(); // Paso 2: Obtener becas disponibles

    Invoice generateInvoice(Student student, List<Course> courses, Scholarship scholarship); // Paso 3: Generación de
                                                                                             // factura

    boolean processPayment(String paymentMethod, double amount); // Paso 4: Proceso de pago

    String confirmEnrollment(); // Paso 5: Confirmación de matrícula
}
