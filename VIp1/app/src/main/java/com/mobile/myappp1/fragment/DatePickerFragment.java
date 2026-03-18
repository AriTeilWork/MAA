package com.mobile.myappp1.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    public interface DatePickerListener {
        void onDateSelected(int year, int month, int day);
    }

    private DatePickerListener listener;

    public void setListener(DatePickerListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(requireActivity(), (view, y, m, d) -> {
            if (listener != null) {
                listener.onDateSelected(y, m, d);
            }
        }, year, month, day);

        return dialog;
    }
}