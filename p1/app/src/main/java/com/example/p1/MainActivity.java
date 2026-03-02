package com.example.p1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private BlogViewModel viewModel;
    private BlogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this)
                .get(BlogViewModel.class);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etComment = findViewById(R.id.etComment);
        EditText etSearchText = findViewById(R.id.etSearchText);
        EditText etSearchDate = findViewById(R.id.etSearchDate);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnSearch = findViewById(R.id.btnSearch);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new BlogAdapter(viewModel.getEntries(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        btnSubmit.setOnClickListener(v -> {

            boolean valid = true;

            if (etUsername.getText().toString().isEmpty()) {
                etUsername.setBackgroundColor(Color.RED);
                valid = false;
            }

            if (etComment.getText().toString().isEmpty()) {
                etComment.setBackgroundColor(Color.RED);
                valid = false;
            }

            if (valid) {
                viewModel.addEntry(
                        etUsername.getText().toString(),
                        etComment.getText().toString()
                );

                adapter.update(viewModel.getEntries());

                etUsername.setText("");
                etComment.setText("");
            }
        });


        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {
                etUsername.setBackgroundColor(Color.WHITE);
                etComment.setBackgroundColor(Color.WHITE);
            }
        };

        etUsername.addTextChangedListener(watcher);
        etComment.addTextChangedListener(watcher);


        btnSearch.setOnClickListener(v -> {

            adapter.update(
                    viewModel.search(
                            etSearchText.getText().toString(),
                            etSearchDate.getText().toString()
                    )
            );
        });
    }
}