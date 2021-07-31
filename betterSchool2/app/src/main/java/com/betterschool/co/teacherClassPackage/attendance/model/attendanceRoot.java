package com.betterschool.co.teacherClassPackage.attendance.model;

import com.betterschool.co.students.model.students;

import java.util.List;

public class attendanceRoot {
    private String createDate;
    private String _id;
    private String classId;
    private String classContainerId;
    private List<students> students;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassContainerId() {
        return classContainerId;
    }

    public void setClassContainerId(String classContainerId) {
        this.classContainerId = classContainerId;
    }

    public List<com.betterschool.co.students.model.students> getStudents() {
        return students;
    }

    public void setStudents(List<com.betterschool.co.students.model.students> students) {
        this.students = students;
    }
}
