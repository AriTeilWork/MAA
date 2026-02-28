package com.example.p2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPhone;
    Spinner spinnerEducation;
    CheckBox chkSports, chkReading, chkMusic;
    Button btnSubmit;
    AutoCompleteTextView actvFirst, actvLast, actvEducation;

    ArrayList<String> firstNames = new ArrayList<>();
    ArrayList<String> lastNames = new ArrayList<>();
    ArrayList<String> educations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        spinnerEducation = findViewById(R.id.spinner_education);
        chkSports = findViewById(R.id.chk_sports);
        chkReading = findViewById(R.id.chk_reading);
        chkMusic = findViewById(R.id.chk_music);
        btnSubmit = findViewById(R.id.btn_submit);


        actvFirst = findViewById(R.id.actv_search_first);
        actvLast = findViewById(R.id.actv_search_last);
        actvEducation = findViewById(R.id.actv_search_education);


        String[] educationLevels = getResources().getStringArray(R.array.education_levels);
        spinnerEducation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, educationLevels));


        btnSubmit.setOnClickListener(v -> {
            String first = etFirstName.getText().toString().trim();
            String last = etLastName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String education = spinnerEducation.getSelectedItem().toString();


            ArrayList<String> hobbies = new ArrayList<>();
            if (chkSports.isChecked()) hobbies.add("Sports");
            if (chkReading.isChecked()) hobbies.add("Reading");
            if (chkMusic.isChecked()) hobbies.add("Music");

            if(!first.isEmpty() && !last.isEmpty() && !phone.isEmpty()) {

                String combinedInfo = first + ";" + last + ";" + phone + ";" + education + ";" + String.join(",", hobbies);


                firstNames.add(combinedInfo);
                lastNames.add(combinedInfo);
                educations.add(combinedInfo);


                actvFirst.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, firstNames));
                actvLast.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lastNames));
                actvEducation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, educations));


                etFirstName.setText("");
                etLastName.setText("");
                etPhone.setText("");
                chkSports.setChecked(false);
                chkReading.setChecked(false);
                chkMusic.setChecked(false);
            }
        });
    }
}