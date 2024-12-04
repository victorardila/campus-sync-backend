package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.controllers.request.InvoiceRequestDTO;
import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.services.billing.InvoiceService;
import com.facade.pattern.campus_sync.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Obtener todas las facturas
    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices); // Devuelve 200 OK con la lista de facturas
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con ID: " + id));
    }

    // Crear una nueva factura
    @PostMapping("/add")
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDTO invoiceDTO) {
        // Lógica para crear factura utilizando los detalles de la solicitud
        Invoice createdInvoice = invoiceService.createInvoice(invoiceDTO.getStudent(),
                invoiceDTO.getCourses(), invoiceDTO.getScholarship(), invoiceDTO.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice); // Devuelve 201 CREATED
    }

    // Cancelar una factura
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelInvoice(@PathVariable Long id) {
        boolean isCanceled = invoiceService.deleteInvoice(id);
        if (isCanceled) {
            return ResponseEntity.ok("Factura cancelada correctamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Factura no encontrada con ID: " + id); // Lanza excepción si no se
                                                                                        // encuentra
        }
    }

    // Actualizar una factura (opcional, dependiendo de los requerimientos)
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequestDTO invoiceDTO) {
        boolean updated = invoiceService.updateInvoice(id, invoiceDTO.getStudent(),
                invoiceDTO.getCourses(), invoiceDTO.getScholarship(), invoiceDTO.getAmount());
        if (updated) {
            return ResponseEntity.ok("Factura actualizada correctamente."); // Devuelve 200 OK
        } else {
            throw new ResourceNotFoundException("Factura no encontrada con ID: " + id);
        }
    }
}
