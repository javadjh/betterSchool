package com.betterschool.co.students.detailSingleStudent.model;

public class attendances {
    private String _id;
    private String createDate;
    private String fathersName;
    private String grade;
    private Boolean hasNegativeScore;
    private Boolean hasPositiveScore;
    private Boolean isPresent;
    private String lastName;
    private String melliCode;
    private String name;

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

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
