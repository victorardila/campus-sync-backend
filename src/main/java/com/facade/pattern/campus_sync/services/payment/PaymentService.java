package com.facade.pattern.campus_sync.services.payment;

import com.facade.pattern.campus_sync.domains.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private List<Payment> paymentList = new ArrayList<>();
    private Long currentId = 1L;

    public boolean processPayment(String paymentMethod, double amount, String number, int cvv,
                                  LocalDateTime expirationDate) {
        if (amount <= 0) {
            return false;
        }

        Payment payment = new Payment();
        payment.setId(currentId++);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setNumber(number);
        payment.setCvv(cvv);
        payment.setExpirationDate(expirationDate);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("PENDING");
        payment.setTransactionId("TXN-" + payment.getId()); // Generamos un transactionId

        paymentList.add(payment);

        if (amount > 0) {
            payment.setStatus("COMPLETED");
            return true;
        }
        return false;
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentList.stream().filter(payment -> payment.getId().equals(id)).findFirst();
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentList);
    }

    public boolean cancelPayment(Long id) {
        Optional<Payment> paymentOptional = getPaymentById(id);

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus("CANCELED");
            return true;
        }
        return false;
    }

    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentList.stream()
                .filter(payment -> payment.getTransactionId() != null
                        && payment.getTransactionId().equals(transactionId))
                .findFirst();
    }

    // Nuevo método para cancelar un pago usando transactionId
    public boolean cancelPaymentByTransactionId(String transactionId) {
        Optional<Payment> paymentOptional = getPaymentByTransactionId(transactionId);

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus("CANCELED");
            return true;
        }
        return false;
    }
}