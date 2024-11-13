package com.facade.pattern.campus_sync.services.billing;

import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    // Utilizamos una lista en memoria para almacenar las facturas
    private List<Invoice> invoiceList = new ArrayList<>();
    private Long currentId = 1L; // ID para las facturas, se incrementa para cada nueva factura

    // Método para crear una nueva factura
    public Invoice createInvoice(Student student, List<Course> cursosSeleccionados, Scholarship scholarship,
            double totalAmount) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setCursosSeleccionados(cursosSeleccionados);
        invoice.setDiscount(scholarship != null ? scholarship.getDiscount() : 0); // Si hay beca, aplica descuento
        invoice.setAmount(totalAmount);
        invoice.setId(currentId++); // Asignar un ID único a la factura

        // Guardamos la factura en memoria
        invoiceList.add(invoice);
        return invoice;
    }

    // Método para obtener una factura por ID
    public Optional<Invoice> getInvoiceById(Long id) {
        // Buscamos la factura en la lista por ID
        return invoiceList.stream().filter(invoice -> invoice.getId().equals(id)).findFirst();
    }

    // Método para obtener todas las facturas
    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoiceList); // Retornamos una copia de la lista de facturas
    }

    // Método para actualizar una factura existente
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        // Buscamos la factura por ID
        Optional<Invoice> existingInvoice = getInvoiceById(id);
        if (existingInvoice.isPresent()) {
            Invoice invoice = existingInvoice.get();
            invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
            invoice.setCursosSeleccionados(updatedInvoice.getCursosSeleccionados());
            invoice.setDiscount(updatedInvoice.getDiscount());
            invoice.setAmount(updatedInvoice.getAmount());

            // Actualizamos la factura en memoria
            return invoice;
        }
        return null; // Si no se encuentra la factura, retornamos null
    }

    // Método para eliminar una factura por ID
    public boolean deleteInvoice(Long id) {
        Optional<Invoice> invoiceToDelete = getInvoiceById(id);
        if (invoiceToDelete.isPresent()) {
            invoiceList.remove(invoiceToDelete.get()); // Eliminamos la factura de la lista en memoria
            return true;
        }
        return false; // Si no se encuentra la factura, no se elimina
    }
}
