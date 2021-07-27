package com.betterschool.co.students.model;

public class students {
    private String _id;
    private String name;
    private String lastName;
    private String melliCode;
    private String fathersName;
    private int grade;
    private String createDate;

    //for attendance
    private Boolean isPresent = true;
    private Boolean hasNegativeScore = false;
    private Boolean hasPositiveScore = false;

    //for score
    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public Boolean getHasNegativeScore() {
        return hasNegativeScore;
    }

    public void setHasNegativeScore(Boolean hasNegativeScore) {
        this.hasNegativeScore = hasNegativeScore;
    }

    public Boolean getHasPositiveScore() {
        return hasPositiveScore;
    }

    public void setHasPositiveScore(Boolean hasPositiveScore) {
        this.hasPositiveScore = hasPositiveScore;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMelliCode() {
        return melliCode;
    }

    public void setMelliCode(String melliCode) {
        this.melliCode = melliCode;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
