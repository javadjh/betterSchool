package com.betterschool.co.students.detailSingleStudent.model;

import java.util.List;

public class classContainers {
    private String _id;
    private String name;
    private String semesterName;
    private List<String> students;
    private List<classesString> classesString;

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

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public List<classesString> getClasses() {
        return classesString;
    }

    public void setClasses(List<classesString> classesString) {
        this.classesString = classesString;
    }
}
