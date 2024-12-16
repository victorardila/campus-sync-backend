package com.facade.pattern.campus_sync.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String academicProgram;
    private String tipoDescuento; // "beca", "votaciones", "descendencia"
    private double saldoPagar;
    private int creditosAcumulados;
    private String username;
    private String password;
    private double money;

    public Student() {
    }

    public Student(Long id, String name, String academicProgram, String tipoDescuento, double saldoPagar,
            int creditosAcumulados, String username, String password, double money) {
        this.id = id;
        this.name = name;
        this.academicProgram = academicProgram;
        this.tipoDescuento = tipoDescuento;
        this.saldoPagar = saldoPagar;
        this.creditosAcumulados = creditosAcumulados;
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(String academicProgram) {
        this.academicProgram = academicProgram;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getSaldoPagar() {
        return saldoPagar;
    }

    public void setSaldoPagar(double saldoPagar) {
        this.saldoPagar = saldoPagar;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public void setCreditosAcumulados(int creditosAcumulados) {
        this.creditosAcumulados = creditosAcumulados;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void deductMoney(double amount) {
        this.money -= amount; // Resta el monto
    }

    @Override
    public String toString() {
        return "Student [academicProgram=" + academicProgram + ", creditosAcumulados=" + creditosAcumulados + ", id="
                + id
                + ", money=" + money + ", name=" + name + ", password=" + password + ", saldoPagar=" + saldoPagar
                + ", tipoDescuento=" + tipoDescuento + ", username=" + username + "]";
    }
}
