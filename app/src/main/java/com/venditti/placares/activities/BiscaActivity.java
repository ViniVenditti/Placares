package com.venditti.placares.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.venditti.placares.databinding.ActivityBiscaBinding;
import com.venditti.placares.fragments.PointsFragment;
import com.venditti.placares.fragments.ScoreboardFragment;
import com.venditti.placares.model.BiscaViewModel;

public class BiscaActivity extends AppCompatActivity {

    private ActivityBiscaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiscaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //configuração toolbar
        Toolbar toolbar = binding.toolbarContext.toolbar;
        toolbar.setTitle("Bisca");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Pontuação", PointsFragment.class)
                .add("Placar", ScoreboardFragment.class)
                .create());

        ViewPager viewPager = binding.viewpager;
        viewPager.setAdapter(adapter);
        binding.viewPagerTab.setViewPager(viewPager);

    }


}