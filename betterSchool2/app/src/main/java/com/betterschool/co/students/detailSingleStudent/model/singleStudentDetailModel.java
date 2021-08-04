package com.betterschool.co.students.detailSingleStudent.model;

import com.betterschool.co.classContainer.moveStudent.model.classContainer;
import com.betterschool.co.otherClass.model.otherClasses;
import com.betterschool.co.students.model.students;

import java.util.List;

public class singleStudentDetailModel {
    private students student;
    private classContainers classContainers;
    private List<exams> exams;
    private List<com.betterschool.co.otherClass.model.otherClasses> otherClasses;
    private List<attendances> attendances;
    private List<deputyNotes> deputyNotes;
    private disciplines disciplines;

    public students getStudent() {
        return student;
    }

    public void setStudent(students student) {
        this.student = student;
    }

    public com.betterschool.co.students.detailSingleStudent.model.classContainers getClassContainers() {
        return classContainers;
    }

    public void setClassContainers(com.betterschool.co.students.detailSingleStudent.model.classContainers classContainers) {
        this.classContainers = classContainers;
    }

    public List<com.betterschool.co.students.detailSingleStudent.model.exams> getExams() {
        return exams;
    }

    public void setExams(List<com.betterschool.co.students.detailSingleStudent.model.exams> exams) {
        this.exams = exams;
    }

    public List<com.betterschool.co.otherClass.model.otherClasses> getOtherClasses() {
        return otherClasses;
    }

    public void setOtherClasses(List<com.betterschool.co.otherClass.model.otherClasses> otherClasses) {
        this.otherClasses = otherClasses;
    }

    public List<com.betterschool.co.students.detailSingleStudent.model.attendances> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<com.betterschool.co.students.detailSingleStudent.model.attendances> attendances) {
        this.attendances = attendances;
    }

    public List<com.betterschool.co.students.detailSingleStudent.model.deputyNotes> getDeputyNotes() {
        return deputyNotes;
    }

    public void setDeputyNotes(List<com.betterschool.co.students.detailSingleStudent.model.deputyNotes> deputyNotes) {
        this.deputyNotes = deputyNotes;
    }

    public com.betterschool.co.students.detailSingleStudent.model.disciplines getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(com.betterschool.co.students.detailSingleStudent.model.disciplines disciplines) {
        this.disciplines = disciplines;
    }
}
