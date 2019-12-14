package com.example.finalproject;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class MenuManager {
    Realm realm = Realm.getDefaultInstance();

    Context context;

    public MenuManager(Context context)
    {
        this.context = context;
    }

    public void save(String itemName, String price,String menuImage, String stallName){
        // Instantiate Plain-Old-Java-Object
        MenuItem newMenuItem = new MenuItem();

        // Set parameters of object
        newMenuItem.setItemName(itemName);
        newMenuItem.setPrice(price);
        newMenuItem.setMenuImage(menuImage);
        newMenuItem.setStallName(stallName);

        // Save to realm
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(newMenuItem);
        realm.commitTransaction();

    }

    public void delete(MenuItem menuItem)
    {
        realm.beginTransaction();

        try{
            menuItem.deleteFromRealm();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        realm.commitTransaction();
    }

    public RealmResults<MenuItem> findAll(String search){
        RealmResults<MenuItem> menuItems = realm.where(MenuItem.class).equalTo("stallName", search).findAll();
        return menuItems;
    }

    public MenuItem checkMenuItem(String itemName)
    {
        MenuItem menuItem = realm.where(MenuItem.class).equalTo("itemName", itemName)
                .findFirst();
        return menuItem;
    }

}

