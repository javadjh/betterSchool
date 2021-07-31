package com.betterschool.co.deputyNote.model;

import com.betterschool.co.teacherClassPackage.studentDetail.model.student;

public class deputyNote {
    private String _id;
    private student studentId;
    private typeDeputy typeId;
    private String description;
    private int semesterName;
    private String createDate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public student getStudentId() {
        return studentId;
    }

    public void setStudentId(student studentId) {
        this.studentId = studentId;
    }

    public typeDeputy getTypeId() {
        return typeId;
    }

    public void setTypeId(typeDeputy typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(int semesterName) {
        this.semesterName = semesterName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
