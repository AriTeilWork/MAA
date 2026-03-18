package com.mobile.myappp1.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    public interface TimePickerListener {
        void onTimeSelected(int hour, int minute);
    }

    private TimePickerListener listener;

    public void setListener(TimePickerListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(requireActivity(),
                (view, h, m) -> {
                    if (listener != null) {
                        listener.onTimeSelected(h, m);
                    }
                }, hour, minute, true);
    }
}