package com.facade.pattern.campus_sync.domains;

public class Course {
    private Long id; // Ahora se asignará manualmente en el repositorio en memoria.
    private String code;
    private String name;
    private int credits;
    private String teacher;
    private int currentQuantity;
    private int maximumLimit;
    private String schedule;

    // Constructores
    public Course() {
    }

    public Course(Long id, String code, String name, int credits, String teacher, int currentQuantity, int maximumLimit,
            String schedule) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        this.currentQuantity = currentQuantity;
        this.maximumLimit = maximumLimit;
        this.schedule = schedule;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMaximumLimit() {
        return maximumLimit;
    }

    public void setMaximumLimit(int maximumLimit) {
        this.maximumLimit = maximumLimit;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
