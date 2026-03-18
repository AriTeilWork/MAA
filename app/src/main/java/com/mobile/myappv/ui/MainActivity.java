package com.mobile.myappv.ui;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.myappv.R;
import com.mobile.myappv.adapter.PersonAdapter;
import com.mobile.myappv.model.Person;
import com.mobile.myappv.viewmodel.CatalogViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CatalogViewModel viewModel;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(CatalogViewModel.class);

        // Submitting Area Views
        EditText etFirst = findViewById(R.id.etFirst);
        EditText etLast = findViewById(R.id.etLast);
        EditText etPhone = findViewById(R.id.etPhone);
        Spinner spinnerEducation = findViewById(R.id.spinnerEducation);
        CheckBox chkSports = findViewById(R.id.chkSports);
        CheckBox chkReading = findViewById(R.id.chkReading);
        CheckBox chkMusic = findViewById(R.id.chkMusic);
        Button btnAdd = findViewById(R.id.btnAdd);

        // Searching Area Views
        AutoCompleteTextView searchFirst = findViewById(R.id.searchFirst);
        AutoCompleteTextView searchLast = findViewById(R.id.searchLast);
        AutoCompleteTextView searchPhone = findViewById(R.id.searchPhone);
        AutoCompleteTextView searchEducation = findViewById(R.id.searchEducation);

        ArrayAdapter<CharSequence> spinnerAdapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.education_levels,
                        android.R.layout.simple_spinner_item
                );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEducation.setAdapter(spinnerAdapter);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new PersonAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel.getPeople().observe(this, people -> {
            adapter.updateData(people);
        });

        viewModel.getSearchData().observe(this, data -> {
            ArrayAdapter<String> autoAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_dropdown_item_1line,
                    data
            );
            searchFirst.setAdapter(autoAdapter);
            searchLast.setAdapter(autoAdapter);
            searchPhone.setAdapter(autoAdapter);
            searchEducation.setAdapter(autoAdapter);
        });

        btnAdd.setOnClickListener(v -> {
            List<String> hobbies = new ArrayList<>();
            if (chkSports.isChecked()) hobbies.add("Sports");
            if (chkReading.isChecked()) hobbies.add("Reading");
            if (chkMusic.isChecked()) hobbies.add("Music");

            Person p = new Person(
                    etFirst.getText().toString(),
                    etLast.getText().toString(),
                    etPhone.getText().toString(),
                    spinnerEducation.getSelectedItem().toString(),
                    hobbies
            );

            viewModel.addPerson(p);

            etFirst.setText("");
            etLast.setText("");
            etPhone.setText("");
            chkSports.setChecked(false);
            chkReading.setChecked(false);
            chkMusic.setChecked(false);
            spinnerEducation.setSelection(0);
        });

        searchFirst.setThreshold(1);
        searchLast.setThreshold(1);
        searchPhone.setThreshold(1);
        searchEducation.setThreshold(1);
    }
}