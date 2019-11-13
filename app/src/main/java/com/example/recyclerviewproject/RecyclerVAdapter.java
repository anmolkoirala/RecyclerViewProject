package com.example.recyclerviewproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerVAdapter extends RecyclerView.Adapter<RecyclerVAdapter.MyHolder> {


    private List<User> usrlist = new ArrayList<>();
    private Context context;

    public RecyclerVAdapter(List<User> usrlist, Context context) {
        this.usrlist = usrlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetails_recycler,parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final User userfinal = usrlist.get(position);
        int imageID = Integer.valueOf(userfinal.getImage());
        holder.imageView.setImageResource(imageID);
        holder.textView.setText(userfinal.getName());


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext() , UserDetailDisp.class);
                intent.putExtra("name", userfinal.getName());
                intent.putExtra("dob", userfinal.getDob());
                intent.putExtra("gender", userfinal.getGender());
                intent.putExtra("country", userfinal.getCountry());
                intent.putExtra("email", userfinal.getEmail());
                intent.putExtra("phone", userfinal.getPhone());
                intent.putExtra("address", userfinal.getAddress());
                intent.putExtra("image", userfinal.getImage());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usrlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rvuserimage);
            textView  = itemView.findViewById(R.id.rvusername);
        }
    }
}
