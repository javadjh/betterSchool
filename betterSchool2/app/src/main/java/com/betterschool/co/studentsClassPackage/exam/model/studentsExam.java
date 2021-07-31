package com.betterschool.co.studentsClassPackage.exam.model;

import com.betterschool.co.teacherClassPackage.exam.model.exams;

import java.util.List;

public class studentsExam {
    private List<studentsExamSt> listFirstFinalExam;
    private List<studentsExamSt> listSecondFinalExam;
    private List<studentsExamSt> listFirstExam;
    private List<studentsExamSt> listSecondExam;
    private List<AllStudentsExamModelRoot> exams;

    public List<AllStudentsExamModelRoot> getExams() {
        return exams;
    }

    public void setExams(List<AllStudentsExamModelRoot> exams) {
        this.exams = exams;
    }

    public List<studentsExamSt> getListFirstFinalExam() {
        return listFirstFinalExam;
    }

    public void setListFirstFinalExam(List<studentsExamSt> listFirstFinalExam) {
        this.listFirstFinalExam = listFirstFinalExam;
    }

    public List<studentsExamSt> getListSecondFinalExam() {
        return listSecondFinalExam;
    }

    public void setListSecondFinalExam(List<studentsExamSt> listSecondFinalExam) {
        this.listSecondFinalExam = listSecondFinalExam;
    }

    public List<studentsExamSt> getListFirstExam() {
        return listFirstExam;
    }

    public void setListFirstExam(List<studentsExamSt> listFirstExam) {
        this.listFirstExam = listFirstExam;
    }

    public List<studentsExamSt> getListSecondExam() {
        return listSecondExam;
    }

    public void setListSecondExam(List<studentsExamSt> listSecondExam) {
        this.listSecondExam = listSecondExam;
    }
}
