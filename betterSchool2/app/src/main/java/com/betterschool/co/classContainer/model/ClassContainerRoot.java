package com.betterschool.co.classContainer.model;

import java.util.List;

public class ClassContainerRoot {
    private List<classes> classes;
    private List<String> students;
    private String _id;
    private String name;
    private String semesterName;

    public List<com.betterschool.co.classContainer.model.classes> getClasses() {
        return classes;
    }

    public void setClasses(List<com.betterschool.co.classContainer.model.classes> classes) {
        this.classes = classes;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
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

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
}
