package com.mobile.tvii;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.mobile.tvii.data.MeetingManager;
import com.mobile.tvii.model.Meeting;
import com.mobile.tvii.ui.MeetingAdapter;

import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    private MeetingManager meetingManager;
    private MeetingAdapter adapter;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        meetingManager = new MeetingManager(this);

        MaterialToolbar toolbar = findViewById(R.id.summary_toolbar);
        toolbar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        RecyclerView list = findViewById(R.id.summary_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingAdapter(null);
        list.setAdapter(adapter);

        emptyView = findViewById(R.id.summary_empty);

        if (getIntent() != null && getIntent().hasExtra(IntentExtras.EXTRA_MESSAGE)) {
            String msg = getIntent().getStringExtra(IntentExtras.EXTRA_MESSAGE);
            if (msg != null && findViewById(R.id.summary_root) != null) {
                Snackbar.make(findViewById(R.id.summary_root), msg, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        List<Meeting> all = meetingManager.getAllMeetings();
        adapter.setMeetings(all);
        emptyView.setVisibility(all.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
