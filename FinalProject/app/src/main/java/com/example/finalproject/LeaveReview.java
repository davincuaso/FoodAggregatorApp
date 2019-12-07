package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_leave_review)
public class LeaveReview extends AppCompatActivity {

    @ViewById
    EditText review;

    @ViewById
    ImageView reviewImageView;

    @ViewById
    Button selectImage;

    @ViewById
    Button submitReview;

    @ViewById
    Button backReview;

    @Bean
    ReviewManager realm;
}
