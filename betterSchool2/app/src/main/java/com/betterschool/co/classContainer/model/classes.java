package com.betterschool.co.classContainer.model;

import com.betterschool.co.classContainer.classes.model.teacher;

public class classes {
    private String _id;
    private String name;
    private com.betterschool.co.classContainer.classes.model.teacher teacher;
    private String semesterName;
    private String createDate;
    private int dayStart;
    private String timeStart;
    private String classContainer;
    private String firstExam;
    private String firstFinalExam;
    private String secondExam;
    private String secondFinalExam;

    public int getDayStart() {
        return dayStart;
    }

    public void setDayStart(int dayStart) {
        this.dayStart = dayStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getClassContainer() {
        return classContainer;
    }

    public void setClassContainer(String classContainer) {
        this.classContainer = classContainer;
    }

    public String getFirstExam() {
        return firstExam;
    }

    public void setFirstExam(String firstExam) {
        this.firstExam = firstExam;
    }

    public String getFirstFinalExam() {
        return firstFinalExam;
    }

    public void setFirstFinalExam(String firstFinalExam) {
        this.firstFinalExam = firstFinalExam;
    }

    public String getSecondExam() {
        return secondExam;
    }

    public void setSecondExam(String secondExam) {
        this.secondExam = secondExam;
    }

    public String getSecondFinalExam() {
        return secondFinalExam;
    }

    public void setSecondFinalExam(String secondFinalExam) {
        this.secondFinalExam = secondFinalExam;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.betterschool.co.classContainer.classes.model.teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(com.betterschool.co.classContainer.classes.model.teacher teacher) {
        this.teacher = teacher;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
