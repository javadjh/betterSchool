package com.betterschool.co.classContainer.moveStudent.model;

public class moveStudentModel {
    private String id;
    private String studentId;
    private String classContainer;

    public moveStudentModel(String id, String studentId, String classContainer) {
        this.id = id;
        this.studentId = studentId;
        this.classContainer = classContainer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassContainer() {
        return classContainer;
    }

    public void setClassContainer(String classContainer) {
        this.classContainer = classContainer;
    }
}
