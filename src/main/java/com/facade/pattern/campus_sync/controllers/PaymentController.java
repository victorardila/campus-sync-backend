package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.services.payment.PaymentService;
import com.facade.pattern.campus_sync.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Procesar un pago
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment, @RequestParam Long studentId) {
        Payment processedPayment = paymentService.processPayment(
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getNumber(),
                payment.getCvv(),
                payment.getExpirationDate(),
                studentId); // Asegúrate de pasar el studentId
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment); // 201 CREATED
    }

    // Obtener todos los pagos
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments); // 200 OK
    }

    // Obtener un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));
    }

    // Obtener un pago por transactionId
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        Optional<Payment> payment = paymentService.getPaymentByTransactionId(transactionId);
        return payment.map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Pago no encontrado con transactionId: " + transactionId));
    }

    // Cancelar un pago por ID
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable Long id) {
        boolean isCanceled = paymentService.cancelPayment(id);
        if (isCanceled) {
            return ResponseEntity.ok("Pago cancelado correctamente."); // 200 OK
        } else {
            throw new ResourceNotFoundException("Pago no encontrado con ID: " + id);
        }
    }

    // Cancelar un pago por transactionId
    @PostMapping("/cancel/transaction/{transactionId}")
    public ResponseEntity<String> cancelPaymentByTransactionId(@PathVariable String transactionId) {
        boolean isCanceled = paymentService.cancelPaymentByTransactionId(transactionId);
        if (isCanceled) {
            return ResponseEntity.ok("Pago cancelado correctamente usando transactionId."); // 200 OK
        } else {
            throw new ResourceNotFoundException("Pago no encontrado con transactionId: " + transactionId);
        }
    }

    // Guardar múltiples pagos (batch processing)
    @PostMapping("/batch/save")
    public ResponseEntity<String> saveMultiplePayments(@RequestBody List<Payment> payments,
            @RequestParam Long studentId) {
        for (Payment payment : payments) {
            paymentService.processPayment(
                    payment.getPaymentMethod(),
                    payment.getAmount(),
                    payment.getNumber(),
                    payment.getCvv(),
                    payment.getExpirationDate(),
                    studentId); // Asegúrate de pasar el studentId
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Pagos guardados exitosamente."); // 201 CREATED
    }

}
