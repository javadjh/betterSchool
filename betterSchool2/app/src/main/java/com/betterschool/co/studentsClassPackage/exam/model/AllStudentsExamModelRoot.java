package com.betterschool.co.studentsClassPackage.exam.model;

public class AllStudentsExamModelRoot {
    private String title;
    private String description;
    private String startDate;
    private String type;
    private student student;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.betterschool.co.studentsClassPackage.exam.model.student getStudent() {
        return student;
    }

    public void setStudent(com.betterschool.co.studentsClassPackage.exam.model.student student) {
        this.student = student;
    }
}
