package com.facade.pattern.campus_sync.domains;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy HH:mm:ss")
    private LocalDateTime invoiceDate;
    private int discount;
    private double amount;

    @ManyToMany(cascade = CascadeType.MERGE) // Cambiado a MERGE para evitar problemas con entidades desconectadas
    @JoinTable(name = "invoice_courses", joinColumns = @JoinColumn(name = "invoice_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> cursosSeleccionados;

    @ManyToOne // Establecemos la relación con el estudiante
    @JoinColumn(name = "student_id") // Define la columna que hace referencia al Student
    private Student student;

    // Constructor vacío
    public Invoice() {
    }

    // Constructor con parámetros
    public Invoice(LocalDateTime invoiceDate, int discount, double amount, List<Course> cursosSeleccionados,
            Student student) {
        this.invoiceDate = invoiceDate;
        this.discount = discount;
        this.amount = amount;
        this.cursosSeleccionados = cursosSeleccionados;
        this.student = student;
    }

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", discount=" + discount +
                ", amount=" + amount +
                ", cursosSeleccionados=" + cursosSeleccionados +
                ", student=" + student +
                '}';
    }
}
