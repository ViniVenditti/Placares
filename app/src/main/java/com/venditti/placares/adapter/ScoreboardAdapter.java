package com.venditti.placares.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.venditti.placares.R;
import com.venditti.placares.model.Players;
import com.venditti.placares.model.Points;

import java.util.HashMap;
import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.MyViewHolder>{
    private List<Players> listJogadores;
    private ScoreboardPointsAdapter adapter;
    private Context context;

    public ScoreboardAdapter(Context context,List<Players> listJogadores) {
        this.context = context;
        this.listJogadores = listJogadores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View scoreboard = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_scoreboard_layout, parent, false);
        return new MyViewHolder(scoreboard);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textPlayerScoreboard.setText(listJogadores.get(holder.getAdapterPosition()).getName());
        holder.textTotalGeral.setText(listJogadores.get(holder.getAdapterPosition()).getTotal().toString());
        HashMap<String, Points> points = listJogadores.get(holder.getAdapterPosition()).getPoints();

        if(!points.isEmpty()){
            adapter = new ScoreboardPointsAdapter(points, points.keySet().toString());
            //Define Layout Categoria
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            holder.recyclerPoints.setLayoutManager(layoutManager);
            holder.recyclerPoints.setHasFixedSize(true);
            holder.recyclerPoints.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return listJogadores.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textPlayerScoreboard, textTotalGeral;
        RecyclerView recyclerPoints;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTotalGeral = itemView.findViewById(R.id.textTotalGeral);
            textPlayerScoreboard = itemView.findViewById(R.id.textPlayerScoreboard);
            recyclerPoints = itemView.findViewById(R.id.recyclerPoints);
        }
    }
}
