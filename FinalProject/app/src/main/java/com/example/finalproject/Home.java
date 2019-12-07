package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @ViewById
    Button mapButton;

    @ViewById
    Button reviewButton;

    @Click(R.id.mapButton)
    public void map()
    {
        MapsActivity_.intent(this).start();
    }

    @Click(R.id.reviewButton)
    public void review(){
        LeaveReview_.intent(this).start();
    }


}
