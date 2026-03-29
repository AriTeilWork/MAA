package com.mobile.tvii.util;

import java.util.ArrayList;
import java.util.List;

public final class ParticipantText {

    private ParticipantText() {
    }

    public static List<String> parseList(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<String> out = new ArrayList<>();
        for (String part : text.split("[,;]")) {
            String t = part.trim();
            if (!t.isEmpty()) {
                out.add(t);
            }
        }
        return out;
    }

    public static String formatForEdit(List<String> participants) {
        if (participants == null || participants.isEmpty()) {
            return "";
        }
        return String.join(", ", participants);
    }
}
