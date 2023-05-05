package com.venditti.placares.adapter;

import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.HashMap;
import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private List<Players> listJogadores;
    private String rodada;
    public List<Players> getListJogadores() {
        return listJogadores;
    }

    public void setRodada(String rodada) {
        this.rodada = rodada;
    }

    public PointsAdapter(List<Players> listJogadores, String rodada){
        this.listJogadores = listJogadores;
        this.rodada = rodada;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View placar = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_layout, parent, false);

        return new MyViewHolder(placar);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HashMap<String, Points> listPoints = listJogadores.get(holder.getAdapterPosition()).getPoints();

        holder.editFaz.setText("");
        holder.editFez.setText("");
        holder.textPlayer.setText(listJogadores.get(holder.getAdapterPosition()).getName());
        holder.textPontos.setText(listJogadores.get(holder.getAdapterPosition()).getTotal().toString());

        holder.editFez.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                    listPoints.remove(rodada);
            }
            @Override
            public void afterTextChanged(Editable s) {
                Points points = new Points();
                if(!s.toString().equals("")) {
                    Integer doing = Integer.valueOf(holder.editFaz.getText().toString());
                    Integer done = Integer.valueOf(holder.editFez.getText().toString());
                    points.setDoing(doing);
                    points.setDone(done);
                    points.setSummation(calculaPontuacao(doing, done));
                    listPoints.put(rodada, points);
                    listJogadores.get(holder.getAdapterPosition()).setPoints(listPoints);
                }
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
