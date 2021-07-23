package com.betterschool.co.homePage.model;

public class modelHomePage {
    int icon;
    int color;
    String title;
    String description;
    String uniqueId;

    public modelHomePage(int icon, int color, String title, String description, String uniqueId) {
        this.icon = icon;
        this.color = color;
        this.title = title;
        this.description = description;
        this.uniqueId = uniqueId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
