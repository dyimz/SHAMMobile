package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("currentpassword")
    @Expose
    private String currentpassword;

    @SerializedName("newpassword")
    @Expose
    private String newpassword;

    @SerializedName("message")
    @Expose
    private String message;

    public ChangePassword(String currentpassword, String newpassword, String message) {
        this.currentpassword = currentpassword;
        this.newpassword = newpassword;
        this.message = message;
    }

    public ChangePassword() {
    }

    public String getCurrentpassword() {
        return currentpassword;
    }

    public void setCurrentpassword(String currentpassword) {
        this.currentpassword = currentpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
