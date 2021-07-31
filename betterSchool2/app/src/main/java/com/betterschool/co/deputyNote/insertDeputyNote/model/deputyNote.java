package com.betterschool.co.deputyNote.insertDeputyNote.model;

public class deputyNote {
    private String studentId;
    private String typeId;
    private String description;

    public deputyNote(String studentId, String typeId, String description) {
        this.studentId = studentId;
        this.typeId = typeId;
        this.description = description;
    }
}
