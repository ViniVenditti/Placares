package com.venditti.placares.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.venditti.placares.MainActivity;
import com.venditti.placares.R;
import com.venditti.placares.adapter.PointsAdapter;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.helper.DateCustom;
import com.venditti.placares.model.BiscaViewModel;
import com.venditti.placares.model.Players;

import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    private DatabaseReference pointsRef;
    private List<Players> listPlayer = new ArrayList<>();
    private RecyclerView recyclerView;
    private PointsAdapter adapter;
    private BiscaViewModel viewModel;

    public PointsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        listPlayer.clear();
        recoveryPlayers();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_points, container, false);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        adapter = new PointsAdapter(listPlayer);

        viewModel = ViewModelProvider(
                getViewModelStore(),
                BiscaViewModel.ViewModelFactory(

                )).get(BiscaViewModel.class);

        //Configura RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return inflate;
    }

    private void recoveryPlayers() {
        final String[] key = {""};
        ConfigFireBase.getDataGameBiscaReference()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()) {
                            key[0] = s.getKey();
                        }
                        snapshot.child(key[0]).getChildren().forEach(c -> {
                            Players player = c.getValue(Players.class);
                            listPlayer.add(player);
                        });
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}