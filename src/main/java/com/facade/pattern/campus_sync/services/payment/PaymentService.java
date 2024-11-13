package com.facade.pattern.campus_sync.services.payment;

import com.facade.pattern.campus_sync.domains.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    // Lista en memoria para almacenar los pagos
    private List<Payment> paymentList = new ArrayList<>();
    private Long currentId = 1L; // ID para los pagos, se incrementa para cada nuevo pago

    // Método que procesa el pago
    public boolean processPayment(String paymentMethod, double amount, String number, int cvv,
            LocalDateTime expirationDate) {
        // Verificar si el monto es válido
        if (amount <= 0) {
            return false; // Si el monto es negativo o cero, no procesamos el pago
        }

        // Crear una nueva instancia de Payment
        Payment payment = new Payment();
        payment.setId(currentId++); // Asignar un ID único a cada pago
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setNumber(number);
        payment.setCvv(cvv);
        payment.setExpirationDate(expirationDate);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("PENDING"); // Estado inicial del pago

        // Guardar el pago en la lista en memoria
        paymentList.add(payment);

        // Lógica de procesamiento de pago (simulada por ahora)
        if (amount > 0) {
            // Simulamos un pago exitoso
            payment.setStatus("COMPLETED");
            // Actualizamos el estado del pago en la lista
            return true; // El pago fue procesado exitosamente
        }

        // Si algo salió mal, dejamos el estado como "PENDING"
        return false;
    }

    // Método que obtiene un pago por su ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentList.stream().filter(payment -> payment.getId().equals(id)).findFirst();
    }

    // Método para obtener todos los pagos
    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentList); // Retorna una copia de todos los pagos
    }

    // Método para cancelar un pago (cambiar su estado)
    public boolean cancelPayment(Long id) {
        Optional<Payment> paymentOptional = paymentList.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst();

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus("CANCELED"); // Actualiza el estado a "CANCELED"
            // No es necesario guardar, ya está en memoria actualizado
            return true;
        }
        return false; // Si no se encuentra el pago, retorna false
    }

    // Método para obtener un pago por el ID de la transacción
    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentList.stream()
                .filter(payment -> payment.getTransactionId() != null
                        && payment.getTransactionId().equals(transactionId))
                .findFirst();
    }
}
