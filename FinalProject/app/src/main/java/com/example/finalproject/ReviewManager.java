package com.example.finalproject;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

@EBean
public class ReviewManager {
//    RealmConfiguration realmConfiguration1 = new RealmConfiguration.Builder(this).name("reviewRealm").build();
    Realm reviewRealm = Realm.getDefaultInstance();

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
        reviewRealm.beginTransaction();
        reviewRealm.copyToRealmOrUpdate(newReview);
        reviewRealm.commitTransaction();

    }

    public void delete(Review review)
    {
        reviewRealm.beginTransaction();

        try{
            review.deleteFromRealm();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        reviewRealm.commitTransaction();
    }

    public RealmResults<Review> findAll(){
        RealmResults<Review> reviews = reviewRealm.where(Review.class).findAll();
        return reviews;
    }

    public Review checkReviews(String reviews)
    {
        Review review = reviewRealm.where(Review.class).equalTo("reviews", reviews)
                .findFirst();
        return review;
    }

}
