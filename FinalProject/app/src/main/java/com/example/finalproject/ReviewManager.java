package com.example.finalproject;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class ReviewManager {
    Realm realm = Realm.getDefaultInstance();

    Context context;

    public ReviewManager(Context context)
    {
        this.context = context;
    }

    public void save(String reviews, String image){
        // Instantiate Plain-Old-Java-Object
        Review newReview = new Review();

        // Set parameters of object
        newReview.setReview(reviews);
        newReview.setImage(image);

        // Save to realm
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(newReview);
        realm.commitTransaction();

    }

    public void delete(Review review)
    {
        realm.beginTransaction();

        try{
            review.deleteFromRealm();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        realm.commitTransaction();
    }

    public RealmResults<Review> findAll(){
        RealmResults<Review> reviews = realm.where(Review.class).findAll();
        return reviews;
    }

    public Review checkReviews(String reviews)
    {
        Review review = realm.where(Review.class).equalTo("reviews", reviews)
                .findFirst();
        return review;
    }

}
