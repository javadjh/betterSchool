package com.betterschool.co.teacherClassPackage.studentDetail.model;

import com.betterschool.co.teacherClassPackage.teachersFile.model.teachersFile;

import java.util.List;

public class studentDetailRoot {
    private List<scores> scores;
    private List<attendance> attendance;
    private List<teachersFile> files;

    public List<teachersFile> getFiles() {
        return files;
    }

    public void setFiles(List<teachersFile> files) {
        this.files = files;
    }

    public List<com.betterschool.co.teacherClassPackage.studentDetail.model.scores> getScores() {
        return scores;
    }

    public void setScores(List<com.betterschool.co.teacherClassPackage.studentDetail.model.scores> scores) {
        this.scores = scores;
    }

    public List<com.betterschool.co.teacherClassPackage.studentDetail.model.attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<com.betterschool.co.teacherClassPackage.studentDetail.model.attendance> attendance) {
        this.attendance = attendance;
    }
}
