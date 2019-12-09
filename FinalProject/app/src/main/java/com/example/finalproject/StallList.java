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

@EActivity(R.layout.activity_stall_list)
public class StallList extends AppCompatActivity {

    @ViewById
    RecyclerView stallRecyclerView;

    @ViewById
    Button backList;

    @Bean
    StallManager realm;

    @Click(R.id.backList)
    public void next()
    {
        MainActivity_.intent(this).start();
    }

    @AfterViews
    public void init(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        stallRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<Stall> displaystalls = realm.findAll();

        StallAdapter sa = new StallAdapter(this,displaystalls);
        stallRecyclerView.setAdapter(sa);
    }

    public void delete(Stall object)
    {
        realm.delete(object);
    }

}

