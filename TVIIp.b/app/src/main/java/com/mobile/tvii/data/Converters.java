package com.mobile.tvii.data;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public final class Converters {

    private Converters() {
    }

    @TypeConverter
    public static String fromParticipants(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    @TypeConverter
    public static List<String> toParticipants(String value) {
        if (value == null || value.isEmpty()) {
            return new ArrayList<>();
        }
        String[] parts = value.split(";");
        List<String> out = new ArrayList<>();
        for (String p : parts) {
            String t = p.trim();
            if (!t.isEmpty()) {
                out.add(t);
            }
        }
        return out;
    }
}
