package com.betterschool.co.classPackage.model;


public class teachersClassesRoot {
    private String _id;
    private String name;
    private int semesterName;
    private int dayStart;
    private String timeStart;
    private String createDate;
    private com.betterschool.co.classContainer.classes.model.teacher teacher;
    private com.betterschool.co.classContainer.moveStudent.model.classContainer classContainer;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public com.betterschool.co.classContainer.classes.model.teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(com.betterschool.co.classContainer.classes.model.teacher teacher) {
        this.teacher = teacher;
    }

    public com.betterschool.co.classContainer.moveStudent.model.classContainer getClassContainer() {
        return classContainer;
    }

    public void setClassContainer(com.betterschool.co.classContainer.moveStudent.model.classContainer classContainer) {
        this.classContainer = classContainer;
    }
}
