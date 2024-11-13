package com.facade.pattern.campus_sync.controllers.response;

import com.facade.pattern.campus_sync.domains.Student;

// Define una clase LoginResponse para enviar la respuesta como JSON
public class LoginResponse {
    private String token;
    private Student user;

    public LoginResponse(String token, Student user) {
        this.token = token;
        this.user = user;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user) {
        this.user = user;
    }
}