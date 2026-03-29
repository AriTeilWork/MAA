package com.mobile.tvii;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mobile.tvii.data.MeetingManager;
import com.mobile.tvii.model.Meeting;
import com.mobile.tvii.util.DateTimePickerUtil;
import com.mobile.tvii.util.ParticipantText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText inputTitle;
    private AutoCompleteTextView inputPlace;
    private MultiAutoCompleteTextView inputParticipants;
    private TextInputEditText inputDate;
    private TextInputEditText inputTime;

    private MeetingManager meetingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingManager = new MeetingManager(this);

        inputTitle = findViewById(R.id.input_title);
        inputPlace = findViewById(R.id.input_place);
        inputParticipants = findViewById(R.id.input_participants);
        inputDate = findViewById(R.id.input_date);
        inputTime = findViewById(R.id.input_time);


        DateTimePickerUtil.setupDatePicker(this, inputDate);
        DateTimePickerUtil.setupTimePicker(this, inputTime);

        setupAutoCompletes();

        MaterialButton submit = findViewById(R.id.btn_submit);
        MaterialButton summary = findViewById(R.id.btn_summary);
        MaterialButton search = findViewById(R.id.btn_search);
        MaterialButton update = findViewById(R.id.btn_update);

        submit.setOnClickListener(v -> onSubmit());
        summary.setOnClickListener(v -> openSummary(null));
        search.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        update.setOnClickListener(v -> startActivity(new Intent(this, UpdateActivity.class)));
    }

    private void setupAutoCompletes() {

        List<String> places = meetingManager.getUniquePlaces();
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, places);
        inputPlace.setAdapter(placeAdapter);

        List<String> participants = meetingManager.getUniqueParticipants();
        ArrayAdapter<String> participantAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, participants);
        inputParticipants.setAdapter(participantAdapter);
        inputParticipants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void onSubmit() {
        String title = textOf(inputTitle);
        if (title.isEmpty()) {
            Toast.makeText(this, R.string.msg_fill_title, Toast.LENGTH_SHORT).show();
            return;
        }

        Meeting m = new Meeting();
        m.setTitle(title);
        m.setPlace(inputPlace.getText().toString().trim());
        m.setParticipants(ParticipantText.parseList(inputParticipants.getText().toString().trim()));
        m.setDate(textOf(inputDate));
        m.setTime(textOf(inputTime));

        meetingManager.addMeeting(m);


        setupAutoCompletes();

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra(IntentExtras.EXTRA_MESSAGE, getString(R.string.msg_meeting_saved));
        startActivity(intent);

        clearForm();
    }

    private void openSummary(String message) {
        Intent intent = new Intent(this, SummaryActivity.class);
        if (message != null) {
            intent.putExtra(IntentExtras.EXTRA_MESSAGE, message);
        }
        startActivity(intent);
    }

    private void clearForm() {
        inputTitle.setText("");
        inputPlace.setText("");
        inputParticipants.setText("");
        inputDate.setText("");
        inputTime.setText("");
    }

    private static String textOf(TextInputEditText et) {
        if (et == null || et.getText() == null) {
            return "";
        }
        return et.getText().toString().trim();
    }
}
