package com.betterschool.co.classPackage.exam.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class exams {
    private String _id;
    private String title;
    private String description;
    private String startDate;
    private String classId;
    private String classContainerId;
    private String type;
    private String createDate;
    private Boolean hasUpdated;
    private List<students> students;

    public List<com.betterschool.co.students.model.students> getStudents() {
        return students;
    }

    public void setStudents(List<com.betterschool.co.students.model.students> students) {
        this.students = students;
    }

    public Boolean getHasUpdated() {
        return hasUpdated;
    }

    public void setHasUpdated(Boolean hasUpdated) {
        this.hasUpdated = hasUpdated;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
