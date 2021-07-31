package com.betterschool.co.disciplineScore.model;

import com.betterschool.co.deputyNote.model.deputyNote;

import java.util.List;

public class disciplineModel {
    private discipline discipline;
    private List<deputyNote> notes;
    private Float suggestionScore;

    public com.betterschool.co.disciplineScore.model.discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(com.betterschool.co.disciplineScore.model.discipline discipline) {
        this.discipline = discipline;
    }

    public List<deputyNote> getNotes() {
        return notes;
    }

    public void setNotes(List<deputyNote> notes) {
        this.notes = notes;
    }

    public Float getSuggestionScore() {
        return suggestionScore;
    }

    public void setSuggestionScore(Float suggestionScore) {
        this.suggestionScore = suggestionScore;
    }
}
