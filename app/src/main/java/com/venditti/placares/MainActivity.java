package com.venditti.placares;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.venditti.placares.activities.BiscaActivity;
import com.venditti.placares.adapter.PlayerAdapter;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.databinding.ActivityMainBinding;
import com.venditti.placares.helper.DateCustom;
import com.venditti.placares.model.BiscaViewModel;
import com.venditti.placares.model.Players;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PlayerAdapter adapter;
    private String game;
    private BiscaViewModel viewModel;
    AtomicInteger gameCount = new AtomicInteger(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //configuração toolbar
        Toolbar toolbar = binding.toolbarContext.toolbar;
        toolbar.setTitle("Placares");
        setSupportActionBar(toolbar);

        carregaJogosPassados();

        game = binding.game.getSelectedItem().toString();
        Spinner spinner = binding.numberPlayers;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int qtdJogadores = pos+3;

                carregarRecycleView(qtdJogadores);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.btnPlay.setOnClickListener(v -> {
            startGame();
        });
    }

    private void startGame() {
        if(adapter.getMapPlayers().isEmpty()){
            Toast.makeText(getApplicationContext(), "preencher nome dos jogadores", Toast.LENGTH_SHORT).show();
        }else {
            Map<Integer, Players> mapPlayers = adapter.getMapPlayers();
            ArrayList<Players> players = new ArrayList<>(mapPlayers.values());

            players.forEach(p -> p.salvar(game, gameCount.toString()));

            Intent intent = new Intent(getApplicationContext(), BiscaActivity.class);
            startActivity(intent);
        }
    }

    private void carregaJogosPassados() {
        ConfigFireBase.getDataGameBiscaReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getChildren().forEach(s -> {
                    gameCount.getAndIncrement();
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void carregarRecycleView(int count){
        RecyclerView rv = binding.recyclerPlayer;
        //configurar Adapter
        adapter = new PlayerAdapter(count);

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(false);
        rv.setAdapter(adapter);
    }
}