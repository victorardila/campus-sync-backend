package com.facade.pattern.campus_sync.domains;

import java.time.LocalDateTime;
import java.util.List;

public class Invoice {
    private Long id;
    private LocalDateTime invoiceDate;
    private int discount;
    private double amount;
    private List<Course> cursosSeleccionados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Course> getCursosSeleccionados() {
        return cursosSeleccionados;
    }

    public void setCursosSeleccionados(List<Course> cursosSeleccionados) {
        this.cursosSeleccionados = cursosSeleccionados;
    }

}
