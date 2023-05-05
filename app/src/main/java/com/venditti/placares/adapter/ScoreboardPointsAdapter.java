package com.venditti.placares.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.venditti.placares.R;
import com.venditti.placares.model.Points;

import java.util.HashMap;

public class ScoreboardPointsAdapter extends RecyclerView.Adapter<ScoreboardPointsAdapter.MyViewHolder> {

    private HashMap<String, Points> listPoints;
    private String rodada;

    public ScoreboardPointsAdapter(HashMap<String, Points> listPoints, String rodada) {
        this.rodada = rodada;
        this.listPoints = listPoints;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View scoreboardPoints = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_scoreboard_layout, parent, false);
        return new ScoreboardPointsAdapter.MyViewHolder(scoreboardPoints);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textRodadaScoreboard.setText(rodada);
        holder.textDoingScoreboard.setText(String.valueOf(listPoints.get(rodada).getDoing()));
        holder.textDoneScoreboard.setText(String.valueOf(listPoints.get(rodada).getDone()));
        holder.textTotalScoreboard.setText(String.valueOf(listPoints.get(rodada).getSummation()));
    }

    @Override
    public int getItemCount() {
        return listPoints.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textRodadaScoreboard, textDoingScoreboard, textDoneScoreboard, textTotalScoreboard;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textRodadaScoreboard = itemView.findViewById(R.id.textRodadaScoreboard);
            textDoingScoreboard = itemView.findViewById(R.id.textDoingScoreboard);
            textDoneScoreboard = itemView.findViewById(R.id.textDoneScoreboard);
            textTotalScoreboard = itemView.findViewById(R.id.textTotalScoreboard);
        }
    }
}
