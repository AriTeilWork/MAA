package com.mobile.tvii.data;

import android.content.Context;

import com.mobile.tvii.model.Meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class MeetingManager {

    private final MeetingDao dao;

    public MeetingManager(Context context) {
        this.dao = AppDatabase.getInstance(context).meetingDao();
    }

    public void addMeeting(Meeting meeting) {
        if (meeting == null) {
            return;
        }
        meeting.setId(0);
        dao.insert(meeting);
    }

    public List<Meeting> getAllMeetings() {
        List<Meeting> all = dao.getAll();
        return all != null ? all : new ArrayList<>();
    }

    public List<Meeting> searchMeetingByTitle(String title) {
        if (isBlank(title)) {
            return getAllMeetings();
        }
        return dao.findByTitlePattern(likePattern(escapeLike(title)));
    }

    public List<Meeting> searchMeetingByDate(String date) {
        if (isBlank(date)) {
            return getAllMeetings();
        }
        return dao.findByDate(date.trim());
    }

    public List<Meeting> searchMeetingByPlace(String place) {
        if (isBlank(place)) {
            return getAllMeetings();
        }
        return dao.findByPlacePattern(likePattern(escapeLike(place)));
    }

    public List<Meeting> searchMeetingByParticipant(String participant) {
        if (isBlank(participant)) {
            return getAllMeetings();
        }
        return dao.findByParticipantPattern(likePattern(escapeLike(participant)));
    }

    public List<Meeting> searchMeetingsDetailed(String title, String place, String participant, String date, String time) {
        String titlePat = isBlank(title) ? "%" : likePattern(escapeLike(title));
        String placePat = isBlank(place) ? "%" : likePattern(escapeLike(place));
        String partPat = isBlank(participant) ? "%" : likePattern(escapeLike(participant));
        String datePat = isBlank(date) ? "%" : likePattern(escapeLike(date));
        String timePat = isBlank(time) ? "%" : likePattern(escapeLike(time));
        return dao.searchCombined(titlePat, placePat, partPat, datePat, timePat);
    }

    public void updateMeeting(Meeting meeting) {
        if (meeting == null || meeting.getId() <= 0) {
            return;
        }
        dao.update(meeting);
    }

    public void deleteMeeting(Meeting meeting) {
        if (meeting == null || meeting.getId() <= 0) {
            return;
        }
        dao.delete(meeting);
    }

    public Meeting getMeetingById(int id) {
        return dao.getById(id);
    }

    public List<String> getUniquePlaces() {
        return dao.getUniquePlaces();
    }

    public List<String> getUniqueParticipants() {
        List<String> rawRows = dao.getAllParticipantsRaw();
        if (rawRows == null) return new ArrayList<>();
        
        Set<String> uniqueParts = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (String row : rawRows) {
            if (row != null && !row.isEmpty()) {
                // Assuming Converters uses ';' as separator
                String[] split = row.split(";");
                for (String p : split) {
                    String trimmed = p.trim();
                    if (!trimmed.isEmpty()) {
                        uniqueParts.add(trimmed);
                    }
                }
            }
        }
        return new ArrayList<>(uniqueParts);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String escapeLike(String raw) {
        if (raw == null) {
            return "";
        }
        return raw.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
    }

    private static String likePattern(String escapedMiddle) {
        return "%" + escapedMiddle + "%";
    }


    public static List<Meeting> uniqueById(List<Meeting> meetings) {
        Set<Integer> seen = new LinkedHashSet<>();
        List<Meeting> out = new ArrayList<>();
        if (meetings == null) {
            return out;
        }
        for (Meeting m : meetings) {
            if (m != null && seen.add(m.getId())) {
                out.add(m);
            }
        }
        return out;
    }
}
