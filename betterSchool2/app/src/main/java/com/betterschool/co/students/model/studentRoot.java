package com.betterschool.co.students.model;

import java.util.List;

public class studentRoot {
    private int pageId;
    private int eachPerPage;
    private List<students> students;

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

    public List<com.betterschool.co.students.model.students> getStudents() {
        return students;
    }

    public void setStudents(List<com.betterschool.co.students.model.students> students) {
        this.students = students;
    }
}
