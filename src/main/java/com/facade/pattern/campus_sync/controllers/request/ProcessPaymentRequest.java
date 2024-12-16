package com.facade.pattern.campus_sync.controllers.request;

import com.facade.pattern.campus_sync.domains.Payment;

public class ProcessPaymentRequest {
    private Payment payment;
    private Long studentId;

    public ProcessPaymentRequest() {
    }

    public ProcessPaymentRequest(Payment payment, Long studentId) {
        this.payment = payment;
        this.studentId = studentId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
