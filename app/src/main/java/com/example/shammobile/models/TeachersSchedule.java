package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeachersSchedule {
    @SerializedName("monday")
    @Expose
    private List<TeachersSchedule> monday;
    @SerializedName("tuesday")
    @Expose
    private List<TeachersSchedule> tuesday;
    @SerializedName("wednesday")
    @Expose
    private List<TeachersSchedule> wednesday;
    @SerializedName("thursday")
    @Expose
    private List<TeachersSchedule> thursday;
    @SerializedName("friday")
    @Expose
    private List<TeachersSchedule> friday;

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
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("yearID")
    @Expose
    private String yearID;
    @SerializedName("semesterID")
    @Expose
    private String semesterID;

    public TeachersSchedule(List<TeachersSchedule> monday, List<TeachersSchedule> tuesday,
                            List<TeachersSchedule> wednesday, List<TeachersSchedule> thursday,
                            List<TeachersSchedule> friday, String schedID, String day,
                            String sectionID, String sectionName, String curriculumID,
                            String curriculumName, String teacherID, String room, String start,
                            String end, String yearID, String semesterID) {
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
        this.room = room;
        this.start = start;
        this.end = end;
        this.yearID = yearID;
        this.semesterID = semesterID;
    }

    public TeachersSchedule() {
    }

    public List<TeachersSchedule> getMonday() {
        return monday;
    }

    public void setMonday(List<TeachersSchedule> monday) {
        this.monday = monday;
    }

    public List<TeachersSchedule> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<TeachersSchedule> tuesday) {
        this.tuesday = tuesday;
    }

    public List<TeachersSchedule> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<TeachersSchedule> wednesday) {
        this.wednesday = wednesday;
    }

    public List<TeachersSchedule> getThursday() {
        return thursday;
    }

    public void setThursday(List<TeachersSchedule> thursday) {
        this.thursday = thursday;
    }

    public List<TeachersSchedule> getFriday() {
        return friday;
    }

    public void setFriday(List<TeachersSchedule> friday) {
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
