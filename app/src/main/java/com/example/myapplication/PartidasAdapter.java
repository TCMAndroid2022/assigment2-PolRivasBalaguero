package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class PartidasAdapter extends RecyclerView.Adapter<PartidasAdapter.ViewHolder> {
    private List<Partida> data = Collections.emptyList();


    public PartidasAdapter() {
    }

    public void setPartidas(List<Partida> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PartidasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item, parent, false);
       ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partida currentPartida = data.get(position);
        holder.name.setText(currentPartida.user + "");
        holder.points.setText(currentPartida.points);
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.User_id);
            points = itemView.findViewById(R.id.User_task);
        }
    }
}
