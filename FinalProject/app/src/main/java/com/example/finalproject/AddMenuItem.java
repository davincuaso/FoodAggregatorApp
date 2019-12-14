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
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@EActivity(R.layout.activity_add_menu_item)
public class AddMenuItem extends AppCompatActivity {

    @ViewById
    EditText menuItemName;

    @ViewById
    EditText menuItemPrice;

    @ViewById
    ImageView menuItemImage;

    @ViewById
    Button menuItemAddImage;

    @ViewById
    Button addMenuItem;

    @ViewById
    Button backMenuItem;

    @ViewById
    Button seeMenu;

    @Bean
    MenuManager realm;

    @Extra
    public String stallName;

    @Click(R.id.addMenuItem)
    public void addMenuItem() {
        String itemName = menuItemName.getText().toString();
        String price = menuItemPrice.getText().toString();



        if (itemName.matches("") || price.matches((""))) {
            Toast toast = Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            realm.save(itemName, price,itemName+".jpeg", stallName);
            MenuList_.intent(this).start();
        }


    }

    @Click(R.id.backMenuItem)
    public void back(){
        InputStall_.intent(this).start();
    }

    @Click(R.id.seeMenu)
    public void see(){
        MenuList_.intent(this).start();
    }


    @Click(R.id.menuItemAddImage)
    public void selectImage()
    {
        MenuImage_.intent(this).startForResult(0);
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
        String inputMenuItemName = menuItemName.getText().toString();

        File getImageDir = getExternalCacheDir();

        File savedImage = new File(getImageDir,  inputMenuItemName+".jpeg");

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
                .into(menuItemImage);
    }
}

