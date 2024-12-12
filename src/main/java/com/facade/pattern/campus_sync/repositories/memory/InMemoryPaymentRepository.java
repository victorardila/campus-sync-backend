package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.repositories.PaymentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(payments.get(id));
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
}
