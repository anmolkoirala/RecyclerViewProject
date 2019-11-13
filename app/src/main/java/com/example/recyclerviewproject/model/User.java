package com.example.recyclerviewproject.model;

import com.example.recyclerviewproject.R;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String dob;
    private String gender;
    private String country;
    private String email;
    private String phone;
    private String address;
    private String image;

    public User(String name, String dob, String gender, String country, String email, String phone, String address,String image) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public String getName() {

        return name;

    }

    public String getDob() {

        return dob;

    }

    public String getGender()
    {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
//        return R.drawable.dummy;
    }
}
