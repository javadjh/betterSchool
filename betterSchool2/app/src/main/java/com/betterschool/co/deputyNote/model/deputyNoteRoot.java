package com.betterschool.co.deputyNote.model;

import java.util.List;

public class deputyNoteRoot {
    private int pageId;
    private int eachPerPage;
    private List<deputyNote> deputyNote;

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

    public List<com.betterschool.co.deputyNote.model.deputyNote> getDeputyNote() {
        return deputyNote;
    }

    public void setDeputyNote(List<com.betterschool.co.deputyNote.model.deputyNote> deputyNote) {
        this.deputyNote = deputyNote;
    }
}
