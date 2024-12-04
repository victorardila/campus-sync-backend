package com.facade.pattern.campus_sync.domains;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime invoiceDate;
    private int discount;
    private double amount;

    @ManyToMany // Cambia esto según la relación que deseas
    @JoinTable(name = "invoice_courses", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "invoice_id"), // Columna que referencia a Invoice
            inverseJoinColumns = @JoinColumn(name = "course_id") // Columna que referencia a Course
    )
    private List<Course> cursosSeleccionados;

    // Getters y setters

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
