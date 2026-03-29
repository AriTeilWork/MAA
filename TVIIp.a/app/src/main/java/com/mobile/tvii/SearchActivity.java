package com.mobile.tvii;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mobile.tvii.data.MeetingManager;
import com.mobile.tvii.model.Meeting;
import com.mobile.tvii.ui.MeetingAdapter;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private MeetingManager meetingManager;
    private MeetingAdapter adapter;

    private TextInputEditText fieldTitle;
    private TextInputEditText fieldPlace;
    private TextInputEditText fieldParticipant;
    private TextInputEditText fieldDate;
    private TextInputEditText fieldTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        meetingManager = new MeetingManager(this);

        fieldTitle = findViewById(R.id.search_title);
        fieldPlace = findViewById(R.id.search_place);
        fieldParticipant = findViewById(R.id.search_participant);
        fieldDate = findViewById(R.id.search_date);
        fieldTime = findViewById(R.id.search_time);

        RecyclerView results = findViewById(R.id.search_results);
        results.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingAdapter(m ->
                Toast.makeText(this, m.toString(), Toast.LENGTH_LONG).show());
        results.setAdapter(adapter);

        MaterialButton run = findViewById(R.id.btn_run_search);
        run.setOnClickListener(v -> runSearch());
    }

    private void runSearch() {
        List<Meeting> found = meetingManager.searchMeetingsDetailed(
                textOf(fieldTitle),
                textOf(fieldPlace),
                textOf(fieldParticipant),
                textOf(fieldDate),
                textOf(fieldTime)
        );
        adapter.setMeetings(found);
        if (found.isEmpty()) {
            Toast.makeText(this, R.string.summary_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private static String textOf(TextInputEditText et) {
        if (et == null || et.getText() == null) {
            return "";
        }
        return et.getText().toString().trim();
    }
}
