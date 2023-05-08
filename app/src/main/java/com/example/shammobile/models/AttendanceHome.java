package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttendanceHome {
    private List<AttendanceHome> data;

    @SerializedName("schedID")
    @Expose
    private String schedID;
    @SerializedName("sectionID")
    @Expose
    private String sectionID;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("curriculumID")
    @Expose
    private String curriculumID;
    @SerializedName("curriculumName")
    @Expose
    private String curriculumName;
    @SerializedName("teacherID")
    @Expose
    private String teacherID;
    @SerializedName("yearID")
    @Expose
    private String yearID;
    @SerializedName("semesterID")
    @Expose
    private String semesterID;

    public AttendanceHome(List<AttendanceHome> data, String schedID, String sectionID,
                          String sectionName, String curriculumID, String curriculumName,
                          String teacherID, String yearID, String semesterID) {
        this.data = data;
        this.schedID = schedID;
        this.sectionID = sectionID;
        this.sectionName = sectionName;
        this.curriculumID = curriculumID;
        this.curriculumName = curriculumName;
        this.teacherID = teacherID;
        this.yearID = yearID;
        this.semesterID = semesterID;
    }

    public AttendanceHome() {
    }

    public List<AttendanceHome> getData() {
        return data;
    }

    public void setData(List<AttendanceHome> data) {
        this.data = data;
    }

    public String getSchedID() {
        return schedID;
    }

    public void setSchedID(String schedID) {
        this.schedID = schedID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(String curriculumID) {
        this.curriculumID = curriculumID;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getYearID() {
        return yearID;
    }

    public void setYearID(String yearID) {
        this.yearID = yearID;
    }

    public String getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(String semesterID) {
        this.semesterID = semesterID;
    }
}
