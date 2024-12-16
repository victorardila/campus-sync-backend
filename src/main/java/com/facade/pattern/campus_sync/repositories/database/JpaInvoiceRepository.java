package com.facade.pattern.campus_sync.repositories.database;

import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.repositories.InvoiceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInvoiceRepository extends JpaRepository<Invoice, Long>, InvoiceRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
