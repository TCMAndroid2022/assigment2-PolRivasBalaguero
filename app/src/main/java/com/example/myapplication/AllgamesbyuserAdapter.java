package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllgamesbyuserAdapter extends RecyclerView.Adapter<AllgamesbyuserAdapter.ViewHolderPartidasbyuser>{
    private List<Partida> data;
    private TopUsersAdapters.OnNoteListener mOnNoteListener;
    private final RecycleViewInterface recycleViewInterface;

    public AllgamesbyuserAdapter(RecycleViewInterface recycleViewInterface) {
        this.recycleViewInterface=recycleViewInterface;
    }

    public  void setPartidas(List<Partida> data) {

        this.data = data;

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AllgamesbyuserAdapter.ViewHolderPartidasbyuser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.scores,null,false);
        return new AllgamesbyuserAdapter.ViewHolderPartidasbyuser(view, mOnNoteListener, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AllgamesbyuserAdapter.ViewHolderPartidasbyuser holder, int position) {

            Partida currentPartida = data.get(position);
            holder.EtiNombre.setText(currentPartida.user + "");
            holder.EtiTipo.setText(currentPartida.points + "");


    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }



    public static class ViewHolderPartidasbyuser extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView EtiNombre, EtiTipo;
        TopUsersAdapters.OnNoteListener onNoteListener;
        private TopUsersAdapters.ViewHolderPartides listener;

        public ViewHolderPartidasbyuser(@NonNull View itemView, TopUsersAdapters.OnNoteListener onNoteListener, RecycleViewInterface recycleViewInterface) {
            super(itemView);
            EtiNombre= (TextView) itemView.findViewById(R.id.Nombre);
            EtiTipo= (TextView) itemView.findViewById(R.id.Points);
            itemView.setOnClickListener(this);
            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }



        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }

}
