package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facade.pattern.campus_sync.domains.Payment;
import com.facade.pattern.campus_sync.repositories.PaymentRepository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long>, PaymentRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
