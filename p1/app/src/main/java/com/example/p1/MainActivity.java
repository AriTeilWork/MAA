package com.example.p1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPhone;
    Button btnSubmit;
    AutoCompleteTextView actvFirst, actvLast, actvPhone;

    ArrayList<String> firstNames = new ArrayList<>();
    ArrayList<String> lastNames = new ArrayList<>();
    ArrayList<String> phones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        btnSubmit = findViewById(R.id.btn_submit);


        actvFirst = findViewById(R.id.actv_search_first);
        actvLast = findViewById(R.id.actv_search_last);
        actvPhone = findViewById(R.id.actv_search_phone);


        btnSubmit.setOnClickListener(v -> {
            String first = etFirstName.getText().toString().trim();
            String last = etLastName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if(!first.isEmpty() && !last.isEmpty() && !phone.isEmpty()) {

                firstNames.add(first);
                lastNames.add(last);
                phones.add(phone);


                actvFirst.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, firstNames));
                actvLast.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lastNames));
                actvPhone.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, phones));


                etFirstName.setText("");
                etLastName.setText("");
                etPhone.setText("");
            }
        });
    }
}