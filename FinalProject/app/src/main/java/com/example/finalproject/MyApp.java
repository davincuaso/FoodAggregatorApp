package com.example.finalproject;

import android.app.Application;

import io.realm.Realm;

public class MyApp extends Application {
    public void onCreate(){
        super.onCreate();

        //initialize realm and stuff
        Realm.init(this);
    }
}
