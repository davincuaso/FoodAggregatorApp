package com.example.finalproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Stall extends RealmObject {
    @PrimaryKey
    private String stallName;

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    @Override
    public String toString() {
        return "Stall{" +
                "stallName = '" + stallName + '\'' +
        '}';
    }

}
