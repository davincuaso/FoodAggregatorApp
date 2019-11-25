package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class Register extends AppCompatActivity {

    @ViewById
    TextView registerText;

    @ViewById
    EditText usernameRegister;

    @ViewById
    EditText nameRegister;

    @ViewById
    EditText passwordRegister;

    @Bean
    UserManager realm;

}
