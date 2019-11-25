package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
    public class MainActivity extends AppCompatActivity{
        @ViewById
        TextView loginText;

        @ViewById
        EditText username;

        @ViewById
        EditText password;

        @ViewById
        Button loginButton;

        @ViewById
        Button registerButton;


    @AfterViews
    public void checkPermissions()
    {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,

                        Manifest.permission.CAMERA

                )

                .withListener(new BaseMultiplePermissionsListener()
                {
                    public void onPermissionsChecked(MultiplePermissionsReport report)
                    {
                        if (report.areAllPermissionsGranted())
                        {
                            init();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "You must provide permissions for app to run", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                })
                .check();
    }

        public void init(){

        }

        @Click(R.id.loginButton)
        public void login(){

        }

        @Click(R.id.registerButton)
        public void register(){
        Register_.intent(this).start();
    }
    }

