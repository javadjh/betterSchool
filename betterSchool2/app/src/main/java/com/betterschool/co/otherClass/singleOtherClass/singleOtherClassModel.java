package com.betterschool.co.otherClass.singleOtherClass;

import com.betterschool.co.students.model.students;
import com.betterschool.co.teachers.model.teachers;

import java.util.List;

public class singleOtherClassModel {
    private String _id;
    private List<students> studentsId;
    private String title;
    private String description;
    private String minParticipant;
    private String maxParticipant;
    private String startDate;
    private String endDate;
    private String price;
    private String sessionsCount;
    private String timeStart;
    private String grade;
    private String semesterName;
    private String image;
    private Boolean canRegister;
    private teachers teacher;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<students> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(List<students> studentsId) {
        this.studentsId = studentsId;
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

    public String getMinParticipant() {
        return minParticipant;
    }

    public void setMinParticipant(String minParticipant) {
        this.minParticipant = minParticipant;
    }

    public String getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(String maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSessionsCount() {
        return sessionsCount;
    }

    public void setSessionsCount(String sessionsCount) {
        this.sessionsCount = sessionsCount;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getCanRegister() {
        return canRegister;
    }

    public void setCanRegister(Boolean canRegister) {
        this.canRegister = canRegister;
    }

    public teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(teachers teacher) {
        this.teacher = teacher;
    }
}
