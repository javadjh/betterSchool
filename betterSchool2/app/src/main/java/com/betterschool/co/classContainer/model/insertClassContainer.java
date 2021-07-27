package com.betterschool.co.classContainer.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class insertClassContainer {
    private String name;
    private List<students> students;

    public insertClassContainer(String name, List<com.betterschool.co.students.model.students> students) {
        this.name = name;
        this.students = students;
    }
}
