package com.facade.pattern.campus_sync.services.billing;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Crear una nueva factura
    public Invoice createInvoice(Student student, List<Course> courses, Scholarship scholarship, double totalAmount) {
        // Crear un objeto Invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setStudent(student); // Asignar el estudiante
        invoice.setCursosSeleccionados(courses); // Asignar los cursos seleccionados
        invoice.setDiscount(scholarship != null ? scholarship.getDiscount() : 0); // Asignar el descuento si hay beca
        invoice.setAmount(totalAmount); // Asignar el monto total

        return invoiceRepository.save(invoice); // Guardar y devolver la factura
    }

    // Obtener todas las facturas
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Obtener una factura por ID
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    // Actualizar una factura existente
    public boolean updateInvoice(Long id, Student student, List<Course> courses, Scholarship scholarship,
            double totalAmount) {
        Optional<Invoice> existingInvoice = getInvoiceById(id);
        if (existingInvoice.isPresent()) {
            Invoice invoice = existingInvoice.get();
            invoice.setStudent(student);
            invoice.setCursosSeleccionados(courses);
            invoice.setDiscount(scholarship != null ? scholarship.getDiscount() : 0);
            invoice.setAmount(totalAmount);

            invoiceRepository.save(invoice);
            return true; // Retornar true si la actualización fue exitosa
        }
        return false; // Si la factura no existe, retornar false
    }

    // Eliminar una factura por ID
    public boolean deleteInvoice(Long id) {
        Optional<Invoice> invoiceToDelete = getInvoiceById(id);
        if (invoiceToDelete.isPresent()) {
            invoiceRepository.deleteById(id); // Eliminamos la factura del repositorio
            return true;
        }
        return false; // Si no se encuentra la factura, no la eliminamos
    }
}
