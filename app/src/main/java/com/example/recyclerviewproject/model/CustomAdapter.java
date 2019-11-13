package com.example.recyclerviewproject.model;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomAdapter extends ArrayAdapter<String> {

    private int hidingItemIndex;


    public CustomAdapter(@NonNull Context context, int textViewResourceId , String[] objects , int hidingItemIndex) {
        super(context, textViewResourceId, objects);
        this.hidingItemIndex = hidingItemIndex;
    }


    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (position == hidingItemIndex) {
            TextView tv = new TextView(getContext());
            tv.setHeight(0);
            tv.setText("Choose an option");
            tv.setVisibility(View.GONE);
            v = tv;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }

}
