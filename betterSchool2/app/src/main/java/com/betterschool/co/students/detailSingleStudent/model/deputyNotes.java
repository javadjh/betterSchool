package com.betterschool.co.students.detailSingleStudent.model;

public class deputyNotes {
    private String _id;
    private String studentId;
    private typeId typeId;
    private String description;
    private String semesterName;
    private String createDate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public com.betterschool.co.students.detailSingleStudent.model.typeId getTypeId() {
        return typeId;
    }

    public void setTypeId(com.betterschool.co.students.detailSingleStudent.model.typeId typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
