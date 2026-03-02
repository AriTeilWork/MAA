package com.example.p1;

import androidx.lifecycle.ViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BlogViewModel extends ViewModel {

    private List<BlogEntry> entries = new ArrayList<>();
    private int counter = 0;

    public void addEntry(String username, String comment) {
        counter++;

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        String currentDate = sdf.format(new Date());

        entries.add(0, new BlogEntry(counter, username, comment, currentDate));
    }

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public List<BlogEntry> search(String text, String date) {

        List<BlogEntry> result = new ArrayList<>();

        for (BlogEntry entry : entries) {

            boolean matchesText =
                    text == null || text.isEmpty() ||
                            entry.getUsername().toLowerCase().contains(text.toLowerCase()) ||
                            entry.getComment().toLowerCase().contains(text.toLowerCase());

            boolean matchesDate =
                    date == null || date.isEmpty() ||
                            entry.getDate().startsWith(date);

            if (matchesText && matchesDate) {
                result.add(entry);
            }
        }

        return result;
    }
}