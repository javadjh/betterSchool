package com.betterschool.co.classPackage.startClass.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class insertAttendanceModel {
    private List<students> students;
    private String classId;
    private String classContainerId;

    public insertAttendanceModel(List<com.betterschool.co.students.model.students> students, String classId, String classContainerId) {
        this.students = students;
        this.classId = classId;
        this.classContainerId = classContainerId;
    }
}
