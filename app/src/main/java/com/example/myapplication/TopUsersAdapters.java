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

    private List<Partida> data = Collections.emptyList();
    private List<Partida> sumatotal = Collections.emptyList();
    ArrayList<User> userList;

    public TopUsersAdapters() {
    }

    public void setUsers(List<Partida> data) {
        this.data = data;
        sumarpuntuacions();
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
        Partida currentPartida = sumatotal.get(position);
        holder.user.setText(currentPartida.user + "");
        holder.points.setText(currentPartida.points + "");

    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public void sumarpuntuacions(){
        boolean trobat;
        for (int x=0;x<data.size() ;x++){
            trobat=false;
            for (int y=0; y<sumatotal.size() && !trobat ;y++){
               if(data.get(x).user.equals(sumatotal.get(y).user)){
                    sumatotal.get(y).points+=data.get(x).points;
                    trobat=true;
                }
            }

            if(!trobat){
                sumatotal.add(new Partida(data.get(x).user,data.get(x).points));
            }
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user;
        public TextView points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.Nombre);
            points = itemView.findViewById(R.id.Points);
        }
    }
}
