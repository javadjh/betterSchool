package com.betterschool.co.classContainer.classes.model;

public class ClassRoot {
    private String _id;
    private String name;
    private int semesterName;
    private int dayStart;
    private String timeStart;
    private String classContainer;
    private String createDate;
    private teacher teacher;

    private String firstExam;
    private String firstFinalExam;
    private String secondExam;
    private String secondFinalExam;

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

    public com.betterschool.co.classContainer.classes.model.teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(com.betterschool.co.classContainer.classes.model.teacher teacher) {
        this.teacher = teacher;
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

    public int getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(int semesterName) {
        this.semesterName = semesterName;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
