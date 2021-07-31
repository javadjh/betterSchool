package com.betterschool.co.teacherClassPackage.note.model;

public class notes {
    private String title;
    private String description;
    private String classId;
    private String createDate;

    public notes() {
    }

    public notes(String title, String description, String classId) {
        this.title = title;
        this.description = description;
        this.classId = classId;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
