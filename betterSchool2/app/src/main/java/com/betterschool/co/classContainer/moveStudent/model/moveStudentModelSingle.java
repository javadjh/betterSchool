package com.betterschool.co.classContainer.moveStudent.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class moveStudentModelSingle {
    private List<classContainer> classContainer;
    private studentsObject students;

    public void setStudents(studentsObject students) {
        this.students = students;
    }

    public List<com.betterschool.co.classContainer.moveStudent.model.classContainer> getClassContainer() {
        return classContainer;
    }

    public void setClassContainer(List<com.betterschool.co.classContainer.moveStudent.model.classContainer> classContainer) {
        this.classContainer = classContainer;
    }

    public studentsObject getStudents() {
        return students;
    }
}
