package com.venditti.placares.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.venditti.placares.R;
import com.venditti.placares.model.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private int count;
    private Context context;
    private Map<Integer, Players> mapPlayers = new HashMap<Integer, Players>();

    public PlayerAdapter(int count){
        this.count = count;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View players = LayoutInflater.from(parent.getContext()).inflate(R.layout.jogador_layout, parent, false);
        context = parent.getContext();
        return new MyViewHolder(players);
    }

    public Map<Integer, Players> getMapPlayers(){
        return mapPlayers;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.editPlayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                mapPlayers.put(holder.getAdapterPosition(), new Players(Objects.requireNonNull(holder.editPlayer.getText()).toString()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText editPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editPlayer = itemView.findViewById(R.id.editJogador);
        }

    }


}
