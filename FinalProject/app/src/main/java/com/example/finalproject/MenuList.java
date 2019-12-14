package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

@EActivity(R.layout.activity_menu_list)
public class MenuList extends AppCompatActivity {

    @ViewById
    RecyclerView menuRecyclerView;

    @ViewById
    Button backMenuRecyclerView;


    @ViewById
    EditText search;

    @ViewById
    Button query;

    @Bean
    MenuManager realm;


    @Click(R.id.backMenuRecyclerView)
    public void next() {
        Home_.intent(this).start();
    }


    @AfterViews
    public void init() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        menuRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<MenuItem> displayItemNames = realm.findAll(search.getText().toString());

        // set adapter
        MenuAdapter ma = new MenuAdapter(this, displayItemNames);
        menuRecyclerView.setAdapter(ma);

    }

    @Click(R.id.query)
    public void query(){
        RealmResults<MenuItem> displayItemNames = realm.findAll(search.getText().toString());

        // set adapter
        MenuAdapter ma = new MenuAdapter(this, displayItemNames);
        menuRecyclerView.setAdapter(ma);
    }


    public void delete(MenuItem object) {
        realm.delete(object);
    }
}

