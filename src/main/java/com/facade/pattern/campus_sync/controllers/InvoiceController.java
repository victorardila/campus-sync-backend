package com.facade.pattern.campus_sync.controllers;


import com.facade.pattern.campus_sync.controllers.request.InvoiceRequestDTO;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.services.billing.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/add")
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDTO invoice) {
        // Lógica para crear factura (usando estudiante, cursos, beca, monto total, etc.)
        Invoice createdInvoice = invoiceService.createInvoice(invoice.getStudent(), invoice.getCourses(),
                invoice.getScholarship(), invoice.getAmount());
        return ResponseEntity.ok(createdInvoice);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelInvoice(@PathVariable Long id) {
        boolean isCanceled = invoiceService.deleteInvoice(id);
        if (isCanceled) {
            return ResponseEntity.ok("Factura cancelada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada.");
        }
    }


}

