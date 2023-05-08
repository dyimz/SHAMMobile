package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Announcement {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("announcement")
    @Expose
    private String announcement;

    public Announcement(String id, String announcement) {
        this.id = id;
        this.announcement = announcement;
    }

    public Announcement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
