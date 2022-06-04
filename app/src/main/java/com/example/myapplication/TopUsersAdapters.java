package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopUsersAdapters extends RecyclerView.Adapter<TopUsersAdapters.ViewHolder> {

    private List<User> data = Collections.emptyList();
    ArrayList<User> userList;

    public TopUsersAdapters() {
    }

    public void setUsers(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopUsersAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item, parent, false);
        TopUsersAdapters.ViewHolder vh = new TopUsersAdapters.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TopUsersAdapters.ViewHolder holder, int position) {
        User currentUser = data.get(position);
        holder.id.setText(currentUser.id + "");
        holder.task.setText(currentUser.name);
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView task;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.User_id);
            task = itemView.findViewById(R.id.User_task);
        }
    }
}
