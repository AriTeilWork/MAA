package com.mobile.tvii.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "meetings")
public class Meeting {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String place;
    private List<String> participants;
    private String date;
    private String time;

    public Meeting() {
        this.participants = new ArrayList<>();
    }

    @Ignore
    public Meeting(int id, String title, String place, List<String> participants, String date, String time) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.participants = participants != null ? new ArrayList<>(participants) : new ArrayList<>();
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants != null ? new ArrayList<>(participants) : new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", participants=" + participants +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
