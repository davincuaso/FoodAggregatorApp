package com.example.finalproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Stall extends RealmObject {
    @PrimaryKey
    private String stallname;

    public String getStallname() {
        return stallname;
    }

    public void setStallname(String stallname) {
        this.stallname = stallname;
    }

    @Override
    public String toString() {
        return "Stalls{" stallname'}';
    }

}
