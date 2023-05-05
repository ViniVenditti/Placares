package com.venditti.placares.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.venditti.placares.R;
import com.venditti.placares.model.Players;
import com.venditti.placares.model.Points;

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private List<Players> listJogadores;
    public List<Players> getListJogadores() {
        return listJogadores;
    }

    public PointsAdapter(List<Players> listJogadores){
        this.listJogadores = listJogadores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View placar = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_layout, parent, false);

        return new MyViewHolder(placar);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<Points> listPoints = listJogadores.get(holder.getAdapterPosition()).getPoints();
        holder.editFez.setText("");
        holder.editFaz.setText("");
        holder.textPlayer.setText(listJogadores.get(holder.getAdapterPosition()).getName());
        holder.textPontos.setText(listJogadores.get(holder.getAdapterPosition()).getTotal().toString());
        holder.editFez.setOnFocusChangeListener((v, hasFocus) -> {
            Points points = new Points();
            if(!holder.editFez.getText().toString().isEmpty()) {
                Integer doing = Integer.valueOf(holder.editFaz.getText().toString());
                Integer done = Integer.valueOf(holder.editFez.getText().toString());
                points.setDoing(doing);
                points.setDone(done);
                points.setSummation(calculaPontuacao(doing, done));
                listPoints.add(points);
                listJogadores.get(holder.getAdapterPosition()).setPoints(listPoints);
            }
        });
    }

    private Integer calculaPontuacao(Integer doing, Integer done) {
        if(done.equals(doing))
            return done + 10;
        else
            return done;
    }


    @Override
    public int getItemCount() {
        return listJogadores.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText editFaz, editFez;
        TextView textPlayer, textPontos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textPlayer = itemView.findViewById(R.id.textPlayer);
            editFaz = itemView.findViewById(R.id.editFaz);
            editFez = itemView.findViewById(R.id.editFez);
            textPontos = itemView.findViewById(R.id.textPontos);
        }
    }

}
