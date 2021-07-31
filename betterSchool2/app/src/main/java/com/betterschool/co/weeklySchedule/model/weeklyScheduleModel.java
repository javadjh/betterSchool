package com.betterschool.co.weeklySchedule.model;

import com.betterschool.co.classContainer.model.classes;

import java.util.List;

public class weeklyScheduleModel {
    private String _id;
    private String name;
    private String semesterName;
    private List<classes> classes;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public List<com.betterschool.co.classContainer.model.classes> getClasses() {
        return classes;
    }

    public void setClasses(List<com.betterschool.co.classContainer.model.classes> classes) {
        this.classes = classes;
    }
}
