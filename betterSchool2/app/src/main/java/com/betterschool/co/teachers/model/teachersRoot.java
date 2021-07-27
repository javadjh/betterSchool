package com.betterschool.co.teachers.model;

import java.util.List;

public class teachersRoot {
    private int pageId;
    private int eachPerPage;
    private List<teachers> teachers;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getEachPerPage() {
        return eachPerPage;
    }

    public void setEachPerPage(int eachPerPage) {
        this.eachPerPage = eachPerPage;
    }

    public List<com.betterschool.co.teachers.model.teachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<com.betterschool.co.teachers.model.teachers> teachers) {
        this.teachers = teachers;
    }
}
