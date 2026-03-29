package com.mobile.tvii.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

public final class DateTimePickerUtil {

    private DateTimePickerUtil() {
    }

    public static void setupDatePicker(Context context, EditText editText) {
        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(context, (view, year1, month1, dayOfMonth) -> {
                String date = String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, month1 + 1, year1);
                editText.setText(date);
            }, year, month, day);
            dialog.show();
        });
    }

    public static void setupTimePicker(Context context, EditText editText) {
        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(context, (view, hourOfDay, minute1) -> {
                String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                editText.setText(time);
            }, hour, minute, true);
            dialog.show();
        });
    }
}
