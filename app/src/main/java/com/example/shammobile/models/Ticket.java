package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("ticket")
    @Expose
    private String ticket;
    @SerializedName("message")
    @Expose
    private String message;

    public Ticket(String ticket, String message) {
        this.ticket = ticket;
        this.message = message;
    }

    public Ticket() {
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
