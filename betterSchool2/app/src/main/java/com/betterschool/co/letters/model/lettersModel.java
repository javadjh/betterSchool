package com.betterschool.co.letters.model;

import com.betterschool.co.letters.insertLetter.model.receiverModel;

public class lettersModel {
    private String _id;
    private Boolean seen;
    private String title;
    private String description;
    private String type;
    private String createDate;
    private receiverModel senderInformation;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public receiverModel getSenderInformation() {
        return senderInformation;
    }

    public void setSenderInformation(receiverModel senderInformation) {
        this.senderInformation = senderInformation;
    }
}
