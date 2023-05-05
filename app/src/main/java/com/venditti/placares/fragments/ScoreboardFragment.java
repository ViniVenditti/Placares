package com.venditti.placares.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.venditti.placares.R;
import com.venditti.placares.adapter.PointsAdapter;
import com.venditti.placares.adapter.ScoreboardAdapter;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.databinding.FragmentScoreboardBinding;
import com.venditti.placares.model.Players;
import com.venditti.placares.model.Points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreboardFragment extends Fragment {

    private FragmentScoreboardBinding binding;
    private ScoreboardAdapter adapter;
    private List<Players> listPlayer;
    public static DatabaseReference playerRef = ConfigFireBase.getPlayerRef("Bisca", ConfigFireBase.recoveryGame());;

    public ScoreboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScoreboardBinding.inflate(inflater);

        Bundle dados = getActivity().getIntent().getExtras();
        listPlayer = (ArrayList<Players>) dados.getSerializable("listaJogadores");


        configuraAdapter();
        return binding.getRoot();
    }

    public void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }

    private void configuraAdapter() {
        adapter = new ScoreboardAdapter(getContext(), listPlayer);
        //Configura RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerScoreboard.setLayoutManager(layoutManager);
        binding.recyclerScoreboard.setHasFixedSize(true);
        binding.recyclerScoreboard.setAdapter(adapter);
    }
}