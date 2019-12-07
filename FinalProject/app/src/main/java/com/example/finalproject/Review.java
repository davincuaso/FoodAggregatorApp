package com.example.finalproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject {
    @PrimaryKey
    private String reviews;

    private String image;

    public String getReview() {
        return reviews;
    }

    public void setReview(String reviews) {
        this.reviews = reviews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString(){
        return "Review{" +
                "reviews = '" + reviews + '\'' +
                ", image=" + image +
                '}';
    }
}
