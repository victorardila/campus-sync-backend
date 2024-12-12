package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.repositories.InvoiceRepository;

@Repository
public interface JpaInvoiceRepository extends JpaRepository<Invoice, Long>, InvoiceRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
