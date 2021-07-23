package com.betterschool.co.students.insertStudent.model;

public class insertStudentModel {
    private String name;
    private String lastName;
    private String fathersName;
    private String melliCode;
    private String grade;

    public insertStudentModel(String name, String lastName, String fathersName, String melliCode, String grade) {
        this.name = name;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.melliCode = melliCode;
        this.grade = grade;
    }
}
