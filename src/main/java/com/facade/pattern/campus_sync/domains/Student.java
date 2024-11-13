package com.facade.pattern.campus_sync.domains;

public class Student {
    private String id;
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

    public Student(String id, String name, String academicProgram, String tipoDescuento, double saldoPagar,
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
