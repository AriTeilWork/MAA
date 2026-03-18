package com.mobile.myappv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.mobile.myappv.R;
import com.mobile.myappv.model.Person;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<Person> people;

    public PersonAdapter(List<Person> people) {
        this.people = people;
    }

    public void updateData(List<Person> newList) {
        people = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvEducation, tvHobbies;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEducation = itemView.findViewById(R.id.tvEducation);
            tvHobbies = itemView.findViewById(R.id.tvHobbies);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person = people.get(position);

        holder.tvName.setText(person.getFirstName() + " " + person.getLastName());
        holder.tvPhone.setText(person.getPhone());
        holder.tvEducation.setText("Education: " + person.getEducation());
        
        String hobbiesStr = "";
        if (person.getHobbies() != null) {
            hobbiesStr = String.join(", ", person.getHobbies());
        }
        holder.tvHobbies.setText("Hobbies: " + hobbiesStr);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
