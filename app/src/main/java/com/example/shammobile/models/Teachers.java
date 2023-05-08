package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teachers {
    private List<Teachers> data;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("mname")
    @Expose
    private String mname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("civilstatus")
    @Expose
    private String civilstatus;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("minor")
    @Expose
    private String minor;
    @SerializedName("coordinator")
    @Expose
    private String coordinator;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("numberofteaching")
    @Expose
    private String numberofteaching;
    @SerializedName("educattainment")
    @Expose
    private String educattainment;

    public Teachers(List<Teachers> data, String id, String fname, String mname, String lname,
                    String contact, String email, String image, String gender, String age,
                    String civilstatus, String birthdate, String address, String certificate,
                    String major, String minor, String coordinator, String position,
                    String numberofteaching, String educattainment) {
        this.data = data;
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.contact = contact;
        this.email = email;
        this.image = image;
        this.gender = gender;
        this.age = age;
        this.civilstatus = civilstatus;
        this.birthdate = birthdate;
        this.address = address;
        this.certificate = certificate;
        this.major = major;
        this.minor = minor;
        this.coordinator = coordinator;
        this.position = position;
        this.numberofteaching = numberofteaching;
        this.educattainment = educattainment;
    }

    public Teachers() {
    }

    public List<Teachers> getData() {
        return data;
    }

    public void setData(List<Teachers> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCivilstatus() {
        return civilstatus;
    }

    public void setCivilstatus(String civilstatus) {
        this.civilstatus = civilstatus;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumberofteaching() {
        return numberofteaching;
    }

    public void setNumberofteaching(String numberofteaching) {
        this.numberofteaching = numberofteaching;
    }

    public String getEducattainment() {
        return educattainment;
    }

    public void setEducattainment(String educattainment) {
        this.educattainment = educattainment;
    }
}
