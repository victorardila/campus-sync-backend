package com.facade.pattern.campus_sync.controllers;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.services.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(
            @RequestBody Payment payment) {
        boolean isProcessed = paymentService.processPayment(payment.getPaymentMethod(), payment.getAmount(),
                payment.getNumber(), payment.getCvv(), payment.getExpirationDate());

        if (isProcessed) {
            return ResponseEntity.ok("Pago procesado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el pago.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable Long id) {
        boolean isCanceled = paymentService.cancelPayment(id);
        if (isCanceled) {
            return ResponseEntity.ok("Pago cancelado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pago no encontrado.");
        }
    }

    // Nuevo endpoint para cancelar un pago usando transactionId
    @PostMapping("/cancel/transaction/{transactionId}")
    public ResponseEntity<String> cancelPaymentByTransactionId(@PathVariable String transactionId) {
        boolean isCanceled = paymentService.cancelPaymentByTransactionId(transactionId);
        if (isCanceled) {
            return ResponseEntity.ok("Pago cancelado correctamente usando transactionId.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pago no encontrado con el transactionId proporcionado.");
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        Optional<Payment> payment = paymentService.getPaymentByTransactionId(transactionId);
        return payment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
