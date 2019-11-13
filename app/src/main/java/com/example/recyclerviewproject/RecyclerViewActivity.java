package com.example.recyclerviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.userrecycler);

        Intent intent = getIntent();
        final List<User>listUser = (List<User>) intent.getSerializableExtra("userlist");

        String[] userNames = new String[listUser.size()];
        String[] userDob = new String[listUser.size()];
        String[] userGender = new String[listUser.size()];
        String[] userCountry = new String[listUser.size()];
        String[] userEmail = new String[listUser.size()];
        String[] userPhone = new String[listUser.size()];
        String[] userAddress = new String[listUser.size()];
        String[] userimage = new String[listUser.size()];

        int i = 0 ;
        for(User user:listUser){
            userNames[i]   = user.getName();
            userDob[i]     = user.getDob();
            userGender[i]  = user.getGender();
            userCountry[i] = user.getCountry();
            userEmail[i]   = user.getEmail();
            userPhone[i]   = user.getPhone();
            userAddress[i] = user.getAddress();
            userimage[i]   = user.getImage();
            userArrayList.add(new User(userNames[i],  userDob[i]  ,  userGender[i]  ,userCountry[i],  userEmail[i]  , userPhone[i]  , userAddress[i] , userimage[i] ));
            i++;
        }

        RecyclerVAdapter adapter = new RecyclerVAdapter(userArrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
