package com.betterschool.co.login.model;

public class LoginAction {
    private String melliCode;
    private String password;
    private String department;

    public LoginAction(String melliCode, String password, String department) {
        this.melliCode = melliCode;
        this.password = password;
        this.department = department;
    }
}
