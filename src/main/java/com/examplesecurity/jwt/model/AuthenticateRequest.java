package com.examplesecurity.jwt.model;

public class AuthenticateRequest {
    private String userName;
    private String password;

    public AuthenticateRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    //for serialization
    public AuthenticateRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
