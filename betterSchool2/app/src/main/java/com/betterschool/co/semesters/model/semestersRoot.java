package com.betterschool.co.semesters.model;

import java.util.List;

public class semestersRoot {
    private int currentSemester;
    private List<semesters> semesters;

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public List<com.betterschool.co.semesters.model.semesters> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<com.betterschool.co.semesters.model.semesters> semesters) {
        this.semesters = semesters;
    }
}
