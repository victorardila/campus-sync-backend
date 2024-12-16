package com.facade.pattern.campus_sync.controllers.request;

import com.facade.pattern.campus_sync.domains.Course;
import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.domains.Student;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InvoiceRequestDTO {
    @NotNull
    private Student student;
    @NotNull
    @Size(min = 1)
    private List<Course> courses;
    @NotNull
    private Scholarship scholarship;
    private double amount;

    public InvoiceRequestDTO() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
