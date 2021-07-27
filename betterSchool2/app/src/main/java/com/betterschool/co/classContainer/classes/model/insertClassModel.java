package com.betterschool.co.classContainer.classes.model;

public class insertClassModel {
    private String name;
    private String teacher;
    private String dayStart;
    private String timeStart;
    private String classContainer;

    public insertClassModel(String name, String teacher, String dayStart, String timeStart, String classContainer) {
        this.name = name;
        this.teacher = teacher;
        this.dayStart = dayStart;
        this.timeStart = timeStart;
        this.classContainer = classContainer;
    }
}
