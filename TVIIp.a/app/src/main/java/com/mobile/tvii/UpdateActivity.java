package com.mobile.tvii;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mobile.tvii.data.MeetingManager;
import com.mobile.tvii.model.Meeting;
import com.mobile.tvii.ui.MeetingAdapter;
import com.mobile.tvii.util.DateTimePickerUtil;
import com.mobile.tvii.util.ParticipantText;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private MeetingManager meetingManager;
    private MeetingAdapter listAdapter;

    private TextInputEditText findTitle;
    private TextInputEditText editTitle;
    private TextInputEditText editPlace;
    private TextInputEditText editParticipants;
    private TextInputEditText editDate;
    private TextInputEditText editTime;
    private TextView idLabel;

    private Meeting selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        meetingManager = new MeetingManager(this);

        findTitle = findViewById(R.id.update_find_title);
        editTitle = findViewById(R.id.update_title);
        editPlace = findViewById(R.id.update_place);
        editParticipants = findViewById(R.id.update_participants);
        editDate = findViewById(R.id.update_date);
        editTime = findViewById(R.id.update_time);
        idLabel = findViewById(R.id.update_selected_id);

        // Setup Date and Time Pickers
        DateTimePickerUtil.setupDatePicker(this, editDate);
        DateTimePickerUtil.setupTimePicker(this, editTime);

        RecyclerView candidates = findViewById(R.id.update_candidates);
        candidates.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new MeetingAdapter(this::applySelection);
        candidates.setAdapter(listAdapter);

        MaterialButton findBtn = findViewById(R.id.btn_find_meetings);
        findBtn.setOnClickListener(v -> findMeetings());

        MaterialButton saveBtn = findViewById(R.id.btn_save_update);
        saveBtn.setOnClickListener(v -> saveChanges());

        selected = null;
        updateIdLabel();

        if (getIntent() != null && getIntent().hasExtra(IntentExtras.EXTRA_MEETING_ID)) {
            int id = getIntent().getIntExtra(IntentExtras.EXTRA_MEETING_ID, -1);
            if (id > 0) {
                Meeting fromDb = meetingManager.getMeetingById(id);
                if (fromDb != null) {
                    applySelection(fromDb);
                }
            }
        }
    }

    private void findMeetings() {
        String q = textOf(findTitle);
        List<Meeting> results;
        if (q.isEmpty()) {
            results = meetingManager.getAllMeetings();
        } else {
            results = meetingManager.searchMeetingByTitle(q);
        }
        listAdapter.setMeetings(results);
        if (results.isEmpty()) {
            Toast.makeText(this, R.string.summary_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void applySelection(Meeting meeting) {
        if (meeting == null) {
            return;
        }
        selected = meeting;
        editTitle.setText(meeting.getTitle());
        editPlace.setText(meeting.getPlace());
        editParticipants.setText(ParticipantText.formatForEdit(meeting.getParticipants()));
        editDate.setText(meeting.getDate());
        editTime.setText(meeting.getTime());
        updateIdLabel();
    }

    private void saveChanges() {
        if (selected == null || selected.getId() <= 0) {
            Toast.makeText(this, R.string.msg_select_meeting, Toast.LENGTH_SHORT).show();
            return;
        }
        String title = textOf(editTitle);
        if (title.isEmpty()) {
            Toast.makeText(this, R.string.msg_fill_title, Toast.LENGTH_SHORT).show();
            return;
        }

        Meeting updated = new Meeting(
                selected.getId(),
                title,
                textOf(editPlace),
                ParticipantText.parseList(textOf(editParticipants)),
                textOf(editDate),
                textOf(editTime)
        );
        meetingManager.updateMeeting(updated);
        Toast.makeText(this, R.string.msg_updated, Toast.LENGTH_SHORT).show();
        findMeetings();
    }

    private void updateIdLabel() {
        if (selected != null && selected.getId() > 0) {
            idLabel.setText(getString(R.string.update_id_label, selected.getId()));
        } else {
            idLabel.setText(R.string.update_id_none);
        }
    }

    private static String textOf(TextInputEditText et) {
        if (et == null || et.getText() == null) {
            return "";
        }
        return et.getText().toString().trim();
    }
}
