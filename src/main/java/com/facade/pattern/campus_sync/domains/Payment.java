package com.facade.pattern.campus_sync.domains;

import java.time.LocalDateTime;

public class Payment {

    private Long id;
    private String paymentMethod;
    private double amount;
    private String number;
    private int cvv;
    private LocalDateTime expirationDate;
    private LocalDateTime paymentDate;
    private String status;
    private String transactionId; // Agregamos el campo transactionId

    // Getters y Setters para todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método getter para transactionId
    public String getTransactionId() {
        return transactionId;
    }

    // Método setter para transactionId
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
