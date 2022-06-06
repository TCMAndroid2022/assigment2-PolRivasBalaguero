package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopUsersAdapters extends RecyclerView.Adapter<TopUsersAdapters.ViewHolderPartides> {
    private OnNoteListener mOnNoteListener;
    private final RecycleViewInterface recycleViewInterface;
    private List<Partida>  data;
    private List<Partida>  sumatotal;
    private View.OnClickListener listener;

    public TopUsersAdapters(RecycleViewInterface RecycleViewInterface) {
        this.recycleViewInterface=RecycleViewInterface;
    }
    public void setOnClicListener(View.OnClickListener listener){
        this.listener = listener;
    }
    public  void setPartidas(List<Partida> data) {

        this.data = data;
        this.sumatotal= new ArrayList<Partida>();
        sumarpuntuacions();

        notifyDataSetChanged();

    }

    public List<Partida> getpartidas(){
        return this.data;
    }

    @NonNull
    @Override
    public TopUsersAdapters.ViewHolderPartides onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.scores,null,false);
        return new ViewHolderPartides(view, mOnNoteListener, recycleViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPartides holder, int position) {

        if(position < sumatotal.size()){
            Partida currentPartida = sumatotal.get(position);
            holder.EtiNombre.setText(currentPartida.user + "");
            holder.EtiTipo.setText(currentPartida.points + "");
        }



    }


    public static class ViewHolderPartides extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView EtiNombre, EtiTipo;
        OnNoteListener onNoteListener;
        private ViewHolderPartides listener;

        public ViewHolderPartides(@NonNull View itemView, OnNoteListener onNoteListener, RecycleViewInterface recycleViewInterface) {
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

    public interface OnNoteListener{
        void oNoteClick(int position);
    }



    @Override
    public int getItemCount() {
        if (sumatotal == null) return 0;
        return sumatotal.size();
    }

    public void sumarpuntuacions(){
        boolean trobat;
        System.out.println("Data size: " + data.size());
        for (int x=0;x<data.size() ;x++){
            trobat=false;
            for (int y=0; y<sumatotal.size() && !trobat ;y++){

               if(data.get(x).user.equals(sumatotal.get(y).user)){
                    int total= Integer.parseInt(sumatotal.get(y).points);
                    int nou = Integer.parseInt(data.get(x).points);
                    System.out.println(data.get((x)).user + " TROBAT");
                    sumatotal.get(y).points=Integer.toString(total+nou);
                    trobat=true;
                }
            }

            if(!trobat){
                sumatotal.add(new Partida(data.get(x).user,data.get(x).points));
                System.out.println(data.get((x)).user +" NOU");

            }
        }
    }

}
