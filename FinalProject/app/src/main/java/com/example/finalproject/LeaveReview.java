package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    @Click(R.id.submitReview)
    public void submitReview() {
        String reviews = review.getText().toString();


        if (reviews.matches("")) {
            Toast toast = Toast.makeText(this, "Please fill up review", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            realm.save(reviews, reviews+".jpeg");
            ReviewList_.intent(this).start();
        }


    }

    @Click(R.id.backReview)
    public void back(){
        Home_.intent(this).start();
    }


    @Click(R.id.selectImage)
    public void selectImage()
    {
        ReviewImage_.intent(this).startForResult(0);
    }

    public void popup(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        if (requestCode==0)
        {
            if (responseCode==100)
            {
                // save rawImage to file savedImage.jpeg
                // load file via picasso
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                try {
                    File savedImage = saveFile(jpeg);
                    refreshImageView(savedImage);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    private File saveFile(byte[] jpeg) throws IOException {
        String inputReview = review.getText().toString();

        File getImageDir = getExternalCacheDir();

        File savedImage = new File(getImageDir,  inputReview+".jpeg");

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    private void refreshImageView(File savedImage) {
        Picasso.with(this)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(reviewImageView);
    }
}
