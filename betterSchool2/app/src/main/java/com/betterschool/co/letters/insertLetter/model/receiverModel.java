package com.betterschool.co.letters.insertLetter.model;

public class receiverModel {
    private String name;
    private String lastName;
    private String userId;

    public receiverModel() {
    }

    public receiverModel(String name, String lastName, String userId) {
        this.name = name;
        this.lastName = lastName;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
