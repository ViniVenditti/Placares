package com.venditti.placares.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.venditti.placares.adapter.PointsAdapter;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.databinding.FragmentPointsBinding;
import com.venditti.placares.model.Players;
import com.venditti.placares.model.Points;

import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    private List<Players> listPlayer;
    private PointsAdapter adapter;
    private FragmentPointsBinding binding;
    private int rodada = 1;
    private int maximoRodada;
    final String[] key = {""};
    public PointsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        recoveryGame();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPointsBinding.inflate(inflater);
        Bundle dados = getActivity().getIntent().getExtras();
        listPlayer = (ArrayList<Players>) dados.getSerializable("listaJogadores");

        configuraAdapter();

        binding.txtRodada.setText("Rodada = " + rodada);
        binding.btnCalcular.setOnClickListener(v -> atualizaPontos());

        getQtdRodada();
        return binding.getRoot();
    }

    private void configuraAdapter() {
        adapter = new PointsAdapter(listPlayer);
        //Configura RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

    private void atualizaPontos() {
        if(rodada < maximoRodada) rodada ++; else rodada--;

        adapter.getListJogadores().forEach(p -> {
            int sum = p.getPoints().stream().mapToInt(Points::getSummation).sum();
            p.setTotal(sum);

            ConfigFireBase.getPlayerRef("Bisca", key[0])
                    .child(p.getName())
                    .setValue(p);
        });
        adapter.notifyDataSetChanged();
        binding.txtRodada.setText("Rodada = " + rodada);
    }

    private void recoveryGame() {

        ConfigFireBase.getDataGameBiscaReference()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()) {
                            key[0] = s.getKey();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

    }

    private void getQtdRodada() {
        switch (listPlayer.size()) {
            case 3:
                maximoRodada = 17;
                break;
            case 4:
                maximoRodada = 12;
                break;
            case 5:
                maximoRodada = 10;
                break;
            case 6:
                maximoRodada = 8;
                break;
            case 7:
                maximoRodada = 7;
                break;
            case 8:
                maximoRodada = 6;
                break;
        }
    }
}