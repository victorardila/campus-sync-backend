package com.facade.pattern.campus_sync.repositories;

import java.util.List;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Notification;
import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.domains.Scholarship;

public interface EnrollmentRepository {

    // Métodos para manejar Course
    Course saveCourse(Course course);

    Course findCourseById(Long id);

    void deleteCourseById(Long id);

    List<Course> findAllCourses();

    // Métodos para manejar Invoice
    Invoice saveInvoice(Invoice invoice);

    Invoice findInvoiceById(Long id);

    void deleteInvoiceById(Long id);

    List<Invoice> findAllInvoices();

    // Métodos para manejar Notification
    Notification saveNotification(Notification notification);

    Notification findNotificationById(Long id);

    void deleteNotificationById(Long id);

    List<Notification> findAllNotifications();

    // Métodos para manejar Payment
    Payment savePayment(Payment payment);

    Payment findPaymentById(Long id);

    void deletePaymentById(Long id);

    List<Payment> findAllPayments();

    // Métodos para manejar Scholarship
    Scholarship saveScholarship(Scholarship scholarship);

    Scholarship findScholarshipById(Long id);

    void deleteScholarshipById(Long id);

    List<Scholarship> findAllScholarships();
}
