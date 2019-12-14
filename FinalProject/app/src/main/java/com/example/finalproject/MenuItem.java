package com.example.finalproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MenuItem extends RealmObject {
    @PrimaryKey
    private String itemName;

    private String price;
    private String menuImage;
    private String stallName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public String getstallName(){
        return stallName;
    }

    public void setStallName(String stallName){
        this.stallName = stallName;
    }

    @Override
    public String toString(){
        return "MenuItem{" +
                "itemName = '" + itemName + '\'' +
                ", price =" + price + '\'' +
                ", menuImage=" + menuImage + "\'" +
                ", stallName=" + stallName +
                '}';
    }
}
