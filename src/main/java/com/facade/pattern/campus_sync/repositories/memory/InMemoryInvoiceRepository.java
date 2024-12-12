package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Invoice;
import com.facade.pattern.campus_sync.repositories.InvoiceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryInvoiceRepository implements InvoiceRepository {

    private Map<Long, Invoice> invoiceMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Invoice save(Invoice invoice) {
        if (invoice.getId() == null) {
            invoice.setId(currentId++);
        }
        invoiceMap.put(invoice.getId(), invoice);
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        return new ArrayList<>(invoiceMap.values());
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return Optional.ofNullable(invoiceMap.get(id));
    }

    @Override
    public void deleteById(Long id) {
        invoiceMap.remove(id);
    }
}
