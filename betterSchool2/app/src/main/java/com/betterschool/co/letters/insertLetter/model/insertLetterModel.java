package com.betterschool.co.letters.insertLetter.model;

public class insertLetterModel {
    private String title;
    private String description;
    private receiverModel receiverInformation;
    private String type;
    private String userId;

    public insertLetterModel(String title, String description, receiverModel receiverInformation, String type, String userId) {
        this.title = title;
        this.description = description;
        this.receiverInformation = receiverInformation;
        this.type = type;
        this.userId = userId;
    }
}
