package com.betterschool.co.classPackage.exam.model;

public class insertExamModel {
    private String title;
    private String description;
    private String startDate;
    private String classId;
    private String classContainerId;
    private String type;

    public insertExamModel(String title, String description, String startDate, String classId, String classContainerId, String type) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.classId = classId;
        this.classContainerId = classContainerId;
        this.type = type;
    }

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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassContainerId() {
        return classContainerId;
    }

    public void setClassContainerId(String classContainerId) {
        this.classContainerId = classContainerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
