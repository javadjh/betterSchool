package com.betterschool.co.teacherClassPackage.mainPageClass.showSingleExam.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class updateExamModel {
    private String id;
    private List<students> students;

    public updateExamModel(String id, List<com.betterschool.co.students.model.students> students) {
        this.id = id;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<com.betterschool.co.students.model.students> getStudents() {
        return students;
    }

    public void setStudents(List<com.betterschool.co.students.model.students> students) {
        this.students = students;
    }
}
