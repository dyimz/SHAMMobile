package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginRequest {
    private List<LoginRequest> data;
    @SerializedName("email")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginRequest(List<LoginRequest> data, String username, String password) {
        this.data = data;
        this.username = username;
        this.password = password;
    }

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

    public List<LoginRequest> getData() {
        return data;
    }

    public void setData(List<LoginRequest> data) {
        this.data = data;
    }
}
