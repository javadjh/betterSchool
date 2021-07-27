package com.betterschool.co.teachers.insertTeacher.model;

public class insertTeacher {
    private String id;
    private String name;
    private String lastName;
    private String melliCode;
    private String title;

    public insertTeacher(String id, String name, String lastName, String melliCode, String title) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.melliCode = melliCode;
        this.title = title;
    }

    public insertTeacher(String name, String lastName, String melliCode, String title) {
        this.name = name;
        this.lastName = lastName;
        this.melliCode = melliCode;
        this.title = title;
    }
}
