package com.facade.pattern.campus_sync.services.payment;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.domains.Student;
import com.facade.pattern.campus_sync.repositories.PaymentRepository;
import com.facade.pattern.campus_sync.services.auth.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private StudentService studentService; // Asegúrate de tener un servicio para manejar

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, StudentService studentService) {
        this.paymentRepository = paymentRepository;
        this.studentService = studentService; // Inicializa el StudentService
    }

    public Payment processPayment(String paymentMethod, double amount, String number, int cvv,
            LocalDateTime expirationDate,
            Long studentId) {
        // Obtén el estudiante por su ID
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Verifica si el estudiante tiene suficiente dinero
        if (student.getMoney() >= amount) {
            // Deduce el dinero del estudiante
            student.deductMoney(amount);
            studentService.updateStudent(studentId, student); // Pasa el ID y el objeto actualizado

            Payment payment = new Payment();
            payment.setPaymentMethod(paymentMethod);
            payment.setAmount(amount);
            payment.setNumber(number);
            payment.setCvv(cvv);
            payment.setExpirationDate(expirationDate);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setStatus("PENDING");
            payment.setTransactionId(
                    "TXN-" + LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC)); // ID único
            // Cambiar el estado a COMPLETED si es exitoso
            payment.setStatus("COMPLETED");
            paymentRepository.save(payment); // Guardar el objeto Payment en el repositorio
            return payment; // Retorna el objeto Payment procesado
        } else {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
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
