package com.example.myapplication.api;

public class LoginCredentials {

    private String email; // Change to email
    private String password;

    public LoginCredentials(String email, String password) {
        this.email = email; // Change to email
        this.password = password;
    }

    public String getEmail() { // Change to email
        return email;
    }

    public void setEmail(String email) { // Change to email
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
