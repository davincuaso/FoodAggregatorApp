package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.RealmResults;

@EActivity(R.layout.activity_review_list)
public class ReviewList extends AppCompatActivity {

    @ViewById
    RecyclerView reviewRecyclerView;

    @ViewById
    Button backReviewRecyclerView;

    @Bean
    ReviewManager realm;

    @Click(R.id.backReviewRecyclerView)
    public void next()
    {
        MainActivity_.intent(this).start();
    }




    @AfterViews
    public void init(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        reviewRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<Review> displayreviews = realm.findAll();

        // set adapter
        ReviewAdapter ra = new ReviewAdapter(this,displayreviews);
        reviewRecyclerView.setAdapter(ra);

    }

    public void delete(Review object)
    {
        realm.delete(object);
    }
}
