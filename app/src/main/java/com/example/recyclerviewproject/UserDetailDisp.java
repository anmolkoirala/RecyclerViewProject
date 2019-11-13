package com.example.recyclerviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserDetailDisp extends AppCompatActivity {


    TextView nametxt, dobtxt, gendertxt, countrytxt, emailtxt, phonetxt, addresstxt;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_disp);

        nametxt = findViewById(R.id.recievedname);
        dobtxt = findViewById(R.id.recieveddob);
        gendertxt = findViewById(R.id.recievedgender);
        countrytxt = findViewById(R.id.recievedcountry);
        emailtxt = findViewById(R.id.recievedemail);
        phonetxt = findViewById(R.id.recievedphone);
        addresstxt = findViewById(R.id.recievedaddress);
        imageView = findViewById(R.id.recievedimage);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String dob = intent.getStringExtra("dob");
        String gender = intent.getStringExtra("gender");
        String country = intent.getStringExtra("country");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String image   = intent.getStringExtra("image");


        int imageID = Integer.valueOf(image);

        nametxt.setText(name);
        dobtxt.setText(dob);
        gendertxt.setText(gender);
        countrytxt.setText(country);
        emailtxt.setText(email);
        phonetxt.setText(phone);
        addresstxt.setText(address);
        imageView.setImageResource(imageID);

    }
}
