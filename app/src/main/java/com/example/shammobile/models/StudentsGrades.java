package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentsGrades {
    private List<StudentsGrades> data;
    @SerializedName("curriculumID")
    @Expose
    private String curriculumID;
    @SerializedName("curriculumName")
    @Expose
    private String curriculumName;
    @SerializedName("teacherID")
    @Expose
    private String teacherID;
    @SerializedName("teacherName")
    @Expose
    private String teacherName;
    @SerializedName("studentID")
    @Expose
    private String studentID;
    @SerializedName("q1")
    @Expose
    private String quarterone;
    @SerializedName("q2")
    @Expose
    private String quartertwo;
    @SerializedName("final")
    @Expose
    private String finalgrade;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("yearID")
    @Expose
    private String yearID;
    @SerializedName("semesterID")
    @Expose
    private String semesterID;

    public StudentsGrades(List<StudentsGrades> data, String curriculumID, String curriculumName,
                          String teacherID, String teacherName, String studentID, String quarterone,
                          String quartertwo, String finalgrade, String remarks, String yearID,
                          String semesterID) {
        this.data = data;
        this.curriculumID = curriculumID;
        this.curriculumName = curriculumName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.studentID = studentID;
        this.quarterone = quarterone;
        this.quartertwo = quartertwo;
        this.finalgrade = finalgrade;
        this.remarks = remarks;
        this.yearID = yearID;
        this.semesterID = semesterID;
    }

    public StudentsGrades() {
    }

    public List<StudentsGrades> getData() {
        return data;
    }

    public void setData(List<StudentsGrades> data) {
        this.data = data;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getQuarterone() {
        return quarterone;
    }

    public void setQuarterone(String quarterone) {
        this.quarterone = quarterone;
    }

    public String getQuartertwo() {
        return quartertwo;
    }

    public void setQuartertwo(String quartertwo) {
        this.quartertwo = quartertwo;
    }

    public String getFinalgrade() {
        return finalgrade;
    }

    public void setFinalgrade(String finalgrade) {
        this.finalgrade = finalgrade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
