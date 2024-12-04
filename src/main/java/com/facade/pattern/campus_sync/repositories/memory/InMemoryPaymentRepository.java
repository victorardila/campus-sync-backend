package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.repositories.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    private final ConcurrentHashMap<Long, Payment> payments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(idGenerator.getAndIncrement());
        }
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }

    @Override
    public Payment findById(Long id) {
        return payments.get(id);
    }

    @Override
    public Optional<Payment> findByTransactionId(String transactionId) {
        return payments.values()
                .stream()
                .filter(payment -> transactionId.equals(payment.getTransactionId()))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        payments.remove(id);
    }

    @Override
    public List<Payment> saveAll(List<Payment> paymentsToSave) {
        for (Payment payment : paymentsToSave) {
            save(payment);
        }
        return paymentsToSave;
    }
}
