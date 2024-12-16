package com.facade.pattern.campus_sync.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.services.academic.CourseService;
import com.facade.pattern.campus_sync.services.academic.ScholarshipService;
import com.facade.pattern.campus_sync.services.billing.InvoiceService;
import com.facade.pattern.campus_sync.services.payment.PaymentService;
import com.facade.pattern.campus_sync.services.notification.NotificationService;

import java.util.List;

@Service
public class EnrollmentFacade implements IEnrollmentFacade {

    @Autowired
    private CourseService courseService;
    private double CREDIT_COST = 150000; // Define el costo por crédito

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<Course> getAvailableCourses() {
        return courseService.getAllCourses(); // Llama al servicio para obtener los cursos
    }

    public double getCreditCost() {
        return CREDIT_COST;
    }

    @Override
    public List<Scholarship> getAvailableScholarships() {
        return scholarshipService.getAllScholarships(); // Llama al servicio para obtener las becas
    }

    @Override
    public Invoice generateInvoice(Student student, List<Course> courses, Scholarship scholarship) {
        double totalAmount = calculateTotalAmount(courses, scholarship);
        return invoiceService.saveInvoice(student, courses, scholarship, totalAmount); // Ahora pasa los parámetros
                                                                                       // correctos
    }

    @Override
    public boolean processPayment(Payment payment, Long studentId) {
        Payment processedPayment = paymentService.processPayment(
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getNumber(),
                payment.getCvv(),
                payment.getExpirationDate(),
                studentId); // Asegúrate de pasar el studentId

        // Devuelve true si el estado del pago es "COMPLETED", "Completed" o "complete"
        return processedPayment.getStatus().equalsIgnoreCase("COMPLETED");
    }

    @Override
    public String confirmEnrollment() {
        // Llama a NotificationService para enviar una notificación de confirmación de
        // matrícula
        notificationService.sendConfirmationNotification("Matrícula Confirmada",
                "Tu matrícula ha sido confirmada con éxito.");
        return "Matrícula confirmada con éxito";
    }

    private double calculateTotalAmount(List<Course> courses, Scholarship scholarship) {
        double subtotal = courses.stream()
                .mapToDouble(course -> course.getCredits() * CREDIT_COST)
                .sum(); // Calcula el subtotal basado en los créditos de los cursos

        double discount = 0.0;
        if (scholarship != null) {
            discount = (scholarship.getDiscount() / 100.0) * subtotal; // Aplica el porcentaje de descuento
        }

        double total = subtotal - discount; // Calcula el total después del descuento
        return total;
    }
}
