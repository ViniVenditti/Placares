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

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private List<Players> listJogadores;

    public List<Players> getListJogadores() {
        return listJogadores;
    }

    public void setListJogadores(List<Players> listJogadores) {
        this.listJogadores = listJogadores;
        notifyDataSetChanged();
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
        if(listJogadores.get(holder.getAdapterPosition()).getDoing() == -1){
            holder.editFez.setText("");
            holder.editFaz.setText("");
        }
        holder.textPlayer.setText(listJogadores.get(holder.getAdapterPosition()).getName());
        holder.textPontos.setText(listJogadores.get(holder.getAdapterPosition()).getPoints().toString());
        holder.editFaz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                listJogadores.get(holder.getAdapterPosition()).setDoing(Integer.valueOf(s.toString().isEmpty() ? "-1" : s.toString()));
            }
        });

        holder.editFez.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                listJogadores.get(holder.getAdapterPosition()).setDone(Integer.valueOf(s.toString().isEmpty() ? "-1" : s.toString()));
            }
        });
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
