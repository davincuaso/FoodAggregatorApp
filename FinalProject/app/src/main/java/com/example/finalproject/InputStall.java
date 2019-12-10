package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_input_stall)
public class InputStall extends AppCompatActivity {

    @ViewById
    EditText inputStall;

    @ViewById
    Button submitStall;

    @Bean
    StallManager realm;

    @Click(R.id.submitStall)
    public void submit() {
        String stallName = inputStall.getText().toString();


        if (stallName.matches("")) {
            Toast toast = Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            realm.save(stallName);
        }

        StallList_.intent(this).start();

    }

    @Click(R.id.seeStalls)
    public void seeStall(){
        StallList_.intent(this).start();
    }
}
