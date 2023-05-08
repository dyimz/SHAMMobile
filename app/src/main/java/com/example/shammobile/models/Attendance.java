package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attendance {
    private List<Attendance> data;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("student_id")
    @Expose
    private String studentID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("teacher_id")
    @Expose
    private String teacherID;
    @SerializedName("curriculum_id")
    @Expose
    private String curriculumID;
    @SerializedName("section_id")
    @Expose
    private String sectionID;
    @SerializedName("year_id")
    @Expose
    private String yearID;
    @SerializedName("semester_id")
    @Expose
    private String semesterID;

    public Attendance(List<Attendance> data, String id, String studentID,
                      String status, String date, String teacherID,
                      String curriculumID, String sectionID, String yearID,
                      String semesterID) {
        this.data = data;
        this.id = id;
        this.studentID = studentID;
        this.status = status;
        this.date = date;
        this.teacherID = teacherID;
        this.curriculumID = curriculumID;
        this.sectionID = sectionID;
        this.yearID = yearID;
        this.semesterID = semesterID;
    }

    public Attendance() {
    }

    public List<Attendance> getData() {
        return data;
    }

    public void setData(List<Attendance> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(String curriculumID) {
        this.curriculumID = curriculumID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
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
