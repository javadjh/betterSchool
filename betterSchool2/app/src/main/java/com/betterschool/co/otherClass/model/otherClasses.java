package com.betterschool.co.otherClass.model;

import com.betterschool.co.classContainer.classes.model.teacher;

public class otherClasses {
    private String _id;
    private String title;
    private Integer minParticipant;
    private Integer maxParticipant;
    private String startDate;
    private String price;
    private Integer grade;
    private String image;
    private String defDate;
    private String studentRegistered;
    private com.betterschool.co.classContainer.classes.model.teacher teacher;

    public String getStudentRegistered() {
        return studentRegistered;
    }

    public void setStudentRegistered(String studentRegistered) {
        this.studentRegistered = studentRegistered;
    }

    public String getDefDate() {
        return defDate;
    }

    public void setDefDate(String defDate) {
        this.defDate = defDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinParticipant() {
        return minParticipant;
    }

    public void setMinParticipant(Integer minParticipant) {
        this.minParticipant = minParticipant;
    }

    public Integer getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(Integer maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public com.betterschool.co.classContainer.classes.model.teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(com.betterschool.co.classContainer.classes.model.teacher teacher) {
        this.teacher = teacher;
    }
}
