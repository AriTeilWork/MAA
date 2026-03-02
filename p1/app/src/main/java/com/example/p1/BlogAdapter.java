package com.example.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private List<BlogEntry> entries;
    private Context context;

    public BlogAdapter(List<BlogEntry> entries, Context context) {
        this.entries = entries;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEntry;

        public ViewHolder(View view) {
            super(view);
            tvEntry = view.findViewById(R.id.tvEntry);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BlogEntry entry = entries.get(position);

        String text =
                context.getString(R.string.entry_number) +
                        entry.getId() + "\n" +
                        entry.getDate() + "\n" +
                        context.getString(R.string.entry_user) +
                        entry.getUsername() + "\n" +
                        entry.getComment();

        holder.tvEntry.setText(text);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void update(List<BlogEntry> newEntries) {
        entries = newEntries;
        notifyDataSetChanged();
    }
}