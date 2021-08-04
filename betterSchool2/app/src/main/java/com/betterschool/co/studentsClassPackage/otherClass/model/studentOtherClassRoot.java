package com.betterschool.co.studentsClassPackage.otherClass.model;

import com.betterschool.co.otherClass.model.otherClasses;

import java.util.List;

public class studentOtherClassRoot {
    private List<otherClasses> otherClassRegistered;
    private List<otherClasses> allOtherClasses;

    public List<otherClasses> getOtherClassRegistered() {
        return otherClassRegistered;
    }

    public void setOtherClassRegistered(List<otherClasses> otherClassRegistered) {
        this.otherClassRegistered = otherClassRegistered;
    }

    public List<otherClasses> getAllOtherClasses() {
        return allOtherClasses;
    }

    public void setAllOtherClasses(List<otherClasses> allOtherClasses) {
        this.allOtherClasses = allOtherClasses;
    }
}
