package com.example.finalproject;


import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class UserManager {
    Realm realm = Realm.getDefaultInstance();

    Context context;

    public UserManager(Context context)
    {
        this.context = context;
    }

    public void save(String name, String username, String password){
        // Instantiate Plain-Old-Java-Object
        User newUser = new User();

        // Set parameters of object
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setPassword(password);

        // Save to realm
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(newUser);
        realm.commitTransaction();

    }

    public void delete(User user)
    {
        realm.beginTransaction();

        try{
            user.deleteFromRealm();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        realm.commitTransaction();
    }

    public RealmResults<User> findAll(){
        RealmResults<User> users = realm.where(User.class).findAll();
        return users;
    }

    public User checkUsers(String username)
    {
        User user = realm.where(User.class).equalTo("username", username)
                .findFirst();
        return user;
    }

}

