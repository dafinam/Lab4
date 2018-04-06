package com.example.dafin.lab4;

/**
 * Created by dafin on 05-Apr-18.
 */

public class User {
    private String device_token;
    private String name;
    private String email;

    public User() {

    }

    public User(String device_token, String name, String email) {
        this.device_token = device_token;
        this.name = name;
        this.email = email;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}