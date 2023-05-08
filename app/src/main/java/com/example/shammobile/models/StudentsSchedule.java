package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentsSchedule {
    @SerializedName("monday")
    @Expose
    private List<StudentsSchedule> monday;
    @SerializedName("tuesday")
    @Expose
    private List<StudentsSchedule> tuesday;
    @SerializedName("wednesday")
    @Expose
    private List<StudentsSchedule> wednesday;
    @SerializedName("thursday")
    @Expose
    private List<StudentsSchedule> thursday;
    @SerializedName("friday")
    @Expose
    private List<StudentsSchedule> friday;

    @SerializedName("schedID")
    @Expose
    private String schedID;
    @SerializedName("day")
    @Expose
    private String day;
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
    @SerializedName("teacherName")
    @Expose
    private String teacherName;
    @SerializedName("studentID")
    @Expose
    private String studentID;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("yearID")
    @Expose
    private String yearID;
    @SerializedName("semesterID")
    @Expose
    private String semesterID;

    public StudentsSchedule(List<StudentsSchedule> monday, List<StudentsSchedule> tuesday,
                            List<StudentsSchedule> wednesday, List<StudentsSchedule> thursday,
                            List<StudentsSchedule> friday, String schedID, String day,
                            String sectionID, String sectionName, String curriculumID,
                            String curriculumName, String teacherID, String teacherName,
                            String studentID, String room, String start, String end, String status,
                            String yearID, String semesterID) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.schedID = schedID;
        this.day = day;
        this.sectionID = sectionID;
        this.sectionName = sectionName;
        this.curriculumID = curriculumID;
        this.curriculumName = curriculumName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.studentID = studentID;
        this.room = room;
        this.start = start;
        this.end = end;
        this.status = status;
        this.yearID = yearID;
        this.semesterID = semesterID;
    }

    public StudentsSchedule() {
    }

    public List<StudentsSchedule> getMonday() {
        return monday;
    }

    public void setMonday(List<StudentsSchedule> monday) {
        this.monday = monday;
    }

    public List<StudentsSchedule> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<StudentsSchedule> tuesday) {
        this.tuesday = tuesday;
    }

    public List<StudentsSchedule> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<StudentsSchedule> wednesday) {
        this.wednesday = wednesday;
    }

    public List<StudentsSchedule> getThursday() {
        return thursday;
    }

    public void setThursday(List<StudentsSchedule> thursday) {
        this.thursday = thursday;
    }

    public List<StudentsSchedule> getFriday() {
        return friday;
    }

    public void setFriday(List<StudentsSchedule> friday) {
        this.friday = friday;
    }

    public String getSchedID() {
        return schedID;
    }

    public void setSchedID(String schedID) {
        this.schedID = schedID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
