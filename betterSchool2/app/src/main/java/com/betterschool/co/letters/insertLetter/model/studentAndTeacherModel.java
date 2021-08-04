package com.betterschool.co.letters.insertLetter.model;

import com.betterschool.co.students.model.students;
import com.betterschool.co.teachers.model.teachers;

import java.util.List;

public class studentAndTeacherModel {
    private List<students> students;
    private List<teachers> teacher;

    public List<com.betterschool.co.students.model.students> getStudents() {
        return students;
    }

    public void setStudents(List<com.betterschool.co.students.model.students> students) {
        this.students = students;
    }

    public List<teachers> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<teachers> teacher) {
        this.teacher = teacher;
    }
}
