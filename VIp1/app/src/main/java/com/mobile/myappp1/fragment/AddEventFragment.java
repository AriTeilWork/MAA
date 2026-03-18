package com.mobile.myappp1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.mobile.myappp1.R;
import com.mobile.myappp1.model.Event;
import com.mobile.myappp1.viewmodel.EventViewModel;

public class AddEventFragment extends Fragment {

    private Spinner spinnerType;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EventViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        spinnerType = view.findViewById(R.id.spinner_type);
        datePicker = view.findViewById(R.id.date_picker);
        timePicker = view.findViewById(R.id.time_picker);
        Button btnSubmit = view.findViewById(R.id.btn_submit);

        timePicker.setIs24HourView(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.event_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> saveEvent());

        return view;
    }

    private void saveEvent() {
        String type = spinnerType.getSelectedItem().toString();
        
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Event event = new Event(type, year, month, day, hour, minute);

        viewModel.addEvent(event);

        Toast.makeText(requireContext(), "Event added", Toast.LENGTH_SHORT).show();

        requireActivity().getSupportFragmentManager().popBackStack();
    }
}