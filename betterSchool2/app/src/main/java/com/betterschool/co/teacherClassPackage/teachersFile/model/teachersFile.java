package com.betterschool.co.teacherClassPackage.teachersFile.model;

public class teachersFile {
    private String _id;
    private String createDate;
    private String title;
    private String classContainerId;
    private String classId;
    private String file;
    private String teacherId;
    private int semesterName;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassContainerId() {
        return classContainerId;
    }

    public void setClassContainerId(String classContainerId) {
        this.classContainerId = classContainerId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(int semesterName) {
        this.semesterName = semesterName;
    }
}
