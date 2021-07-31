package com.betterschool.co.letters.model;

import android.content.Intent;

import java.util.List;

public class letters {
    private String _id;
    private List<String> teachersReceiver;
    private Boolean seen;
    private String title;
    private String description;
    private teacherSender teacherSender;
    private Boolean headmasterReceiver;
    private Integer semesterName;
    private String createDate;
    private String headmasterSender;

    public String getHeadmasterSender() {
        return headmasterSender;
    }

    public void setHeadmasterSender(String headmasterSender) {
        this.headmasterSender = headmasterSender;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getTeachersReceiver() {
        return teachersReceiver;
    }

    public void setTeachersReceiver(List<String> teachersReceiver) {
        this.teachersReceiver = teachersReceiver;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public com.betterschool.co.letters.model.teacherSender getTeacherSender() {
        return teacherSender;
    }

    public void setTeacherSender(com.betterschool.co.letters.model.teacherSender teacherSender) {
        this.teacherSender = teacherSender;
    }

    public Boolean getHeadmasterReceiver() {
        return headmasterReceiver;
    }

    public void setHeadmasterReceiver(Boolean headmasterReceiver) {
        this.headmasterReceiver = headmasterReceiver;
    }

    public Integer getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(Integer semesterName) {
        this.semesterName = semesterName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
