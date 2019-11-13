package com.example.recyclerviewproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.recyclerviewproject.model.CustomAdapter;
import com.example.recyclerviewproject.model.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    AutoCompleteTextView autoCompleteTextView;
    EditText editTextphone, editTextname,editTextdob, editTextemail, editTextaddress;
    RadioGroup radioGroup;
    Spinner spinner;
    Button button, btnView;
    //CalendarView calendarView;
    String name,gender, dob, country, address, email, phone, image;
    String[] countries = {"Select an Option","Nepal", "Bhutan", "India", "Srilanka", "Maldives", "Myanmar", "Pakistan", "Afganistan"};
    List<User> userlist = new ArrayList<>();
    String[] imagesuggestion = {"dummy","dummy2","dummy3"};
    Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String mydateformat = "dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateformat, Locale.getDefault());
            editTextdob.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextname    = findViewById(R.id.tbl_name);
        editTextemail   = findViewById(R.id.tbl_email);
        editTextaddress = findViewById(R.id.tbl_address);
        editTextdob     = findViewById(R.id.tbl_dob);
        radioGroup      = findViewById(R.id.tbl_gender);
        editTextphone   = findViewById(R.id.tbl_phone);
        spinner         = findViewById(R.id.spiinerdata);
        button          = findViewById(R.id.btn_submit);
        btnView         = findViewById(R.id.btn_view);

        //for autocomplete Image name.
        autoCompleteTextView   = findViewById(R.id.userimage);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_item, imagesuggestion
        );
        autoCompleteTextView.setAdapter(stringArrayAdapter);
        autoCompleteTextView.setThreshold(1);


        //Creating a custom Adapter for spinner to display "Choose a option" option.
        int hidingItemIndex = 0;
        CustomAdapter dataAdapter = new CustomAdapter(this, R.layout.spinner_values, countries, hidingItemIndex);
        dataAdapter.setDropDownViewResource(R.layout.spinner_values);

        //using array adapter to pass the value into spinner_values.xml file.
        spinner.setAdapter(dataAdapter);//using set adapter to pass the value into spinner.
        //use listView instead of spinner.

        button.setOnClickListener(this);
        btnView.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        editTextdob.setOnClickListener(this);
        newSpinnerValue();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.tbl_male){
            gender = "Male";
        }

        if(checkedId == R.id.tbl_female){
            gender = "Female";
        }

        if(checkedId == R.id.tbl_other){
            gender = "Other";
        }

    }


    private void newSpinnerValue(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        name    = editTextname.getText().toString();
        dob     = editTextdob.getText().toString();
        address = editTextaddress.getText().toString();
        email   = editTextemail.getText().toString();
        phone   = editTextphone.getText().toString();
        image   = autoCompleteTextView.getText().toString();

        if(v.getId() == R.id.btn_submit) {

            if (validate()) {
                String uri = "@drawable/"+image;
                int resID = getResources().getIdentifier(uri,null,getPackageName());
                String imageID = String.valueOf(resID);

                userlist.add(new User(name,dob,gender, country, email, phone,address,imageID));
                Toast.makeText(this, "Form Data Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == R.id.btn_view) {
            Intent intent = new Intent(this, RecyclerViewActivity.class);
            intent.putExtra("userlist", (Serializable) userlist);
            startActivity(intent);
        }

        if(v.getId() == R.id.tbl_dob){
            new DatePickerDialog(this, mydatepicker,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    //validation
    private boolean validate(){

        if (spinner.getSelectedItem().toString().trim().equals("Select an Option")) {
            Toast.makeText(this, "Select a Country to Proceed", Toast.LENGTH_SHORT).show();
            spinner.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(name)){
            editTextname.setError("Please Enter Name");
            editTextname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(dob)){
            editTextdob.setError("Please Enter DoB");
            editTextdob.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(address)){
            editTextaddress.setError("Please Enter Address");
            editTextaddress.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please Enter Email");
            editTextaddress.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(TextUtils.isEmpty(phone)){
            editTextphone.setError("Please Enter Phone Number");
            editTextphone.requestFocus();
            return false;
        }
        if(!TextUtils.isDigitsOnly(phone)){
            editTextphone.setError("Invalid Phone Number");
            editTextphone.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(image)){
            autoCompleteTextView.setError("Please Enter Image Name");
            autoCompleteTextView.requestFocus();
            return false;
        }
        return true;
    }
}

