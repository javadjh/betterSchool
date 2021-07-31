package com.betterschool.co.deputyNote.insertDeputyNote.model;

public class violations {
    private String _id;
    private Float score;
    private String title;

    public violations() {
    }

    public violations(Float score, String title) {
        this.score = score;
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
