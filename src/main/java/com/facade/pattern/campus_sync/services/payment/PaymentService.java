package com.facade.pattern.campus_sync.services.payment;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Procesar un pago
    public Payment processPayment(String paymentMethod, double amount, String number, int cvv,
            LocalDateTime expirationDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }

        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setNumber(number);
        payment.setCvv(cvv);
        payment.setExpirationDate(expirationDate);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("PENDING");
        payment.setTransactionId(
                "TXN-" + LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC)); // ID
                                                                                       // único

        // Cambiar el estado a COMPLETED si es exitoso
        payment.setStatus("COMPLETED");
        return paymentRepository.save(payment);
    }

    // Obtener todos los pagos
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Obtener un pago por ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Obtener un pago por transactionId
    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }

    // Cancelar un pago por ID
    public boolean cancelPayment(Long id) {
        Optional<Payment> existingPayment = getPaymentById(id);

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setStatus("CANCELED");
            paymentRepository.save(payment); // Guardar el cambio en el repositorio
            return true;
        }
        return false;
    }

    // Cancelar un pago por transactionId
    public boolean cancelPaymentByTransactionId(String transactionId) {
        Optional<Payment> existingPayment = getPaymentByTransactionId(transactionId);

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setStatus("CANCELED");
            paymentRepository.save(payment); // Guardar el cambio en el repositorio
            return true;
        }
        return false;
    }
}
