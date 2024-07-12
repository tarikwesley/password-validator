package com.devturing.password.validator.model;

public class PasswordValidationRequest {
    private String password;

    public PasswordValidationRequest() {
    }

    public PasswordValidationRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
