package com.mobile.tvii.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.tvii.R;
import com.mobile.tvii.model.Meeting;
import com.mobile.tvii.util.ParticipantText;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    public interface OnMeetingClickListener {
        void onMeetingClick(Meeting meeting);
    }

    private final List<Meeting> items = new ArrayList<>();
    private final OnMeetingClickListener clickListener;

    public MeetingAdapter(OnMeetingClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setMeetings(List<Meeting> meetings) {
        items.clear();
        if (meetings != null) {
            items.addAll(meetings);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
        return new MeetingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting m = items.get(position);
        holder.bind(m, clickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MeetingViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView meta;
        private final TextView participants;

        MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            meta = itemView.findViewById(R.id.item_meta);
            participants = itemView.findViewById(R.id.item_participants);
        }

        void bind(Meeting m, OnMeetingClickListener listener) {
            title.setText(m.getTitle() != null ? m.getTitle() : "");
            String place = m.getPlace() != null ? m.getPlace() : "";
            String date = m.getDate() != null ? m.getDate() : "";
            String time = m.getTime() != null ? m.getTime() : "";
            meta.setText(itemView.getContext().getString(R.string.meeting_meta_line, place, date, time));
            participants.setText(itemView.getContext().getString(
                    R.string.meeting_participants_line,
                    ParticipantText.formatForEdit(m.getParticipants())));
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMeetingClick(m);
                }
            });
        }
    }
}
