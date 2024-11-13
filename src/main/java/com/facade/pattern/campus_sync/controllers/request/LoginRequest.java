package com.facade.pattern.campus_sync.controllers.request;

public class LoginRequest {
    private String username;
    private String password;

    // Constructor, getters y setters
    public LoginRequest() {
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
}