package com.betterschool.co.disciplineScore.model;

public class insertDisciplineModel {
    private Float firstScore;
    private Float secondScore;
    private String studentId;

    public insertDisciplineModel(Float firstScore, Float secondScore, String studentId) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.studentId = studentId;
    }
}
