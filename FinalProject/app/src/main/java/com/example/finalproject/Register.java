package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
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

    @AfterViews
    public void next(){

    }

    @Bean
    UserManager realm;


    @Click(R.id.registerButton2)
    public void register() {
        String name = nameRegister.getText().toString();
        String username = usernameRegister.getText().toString();
        String password = passwordRegister.getText().toString();


        if (name.matches("") || username.matches("") || password.matches("")) {
            Toast toast = Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            realm.save(name, username, password);
        }

        MainActivity_.intent(this).start();

    }

    @Click(R.id.backButton)
    public void back(){
        MainActivity_.intent(this).start();
    }

}
