package com.venditti.placares.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.venditti.placares.databinding.ActivityBiscaBinding
import com.venditti.placares.fragments.PointsFragment
import com.venditti.placares.fragments.ScoreboardFragment

class BiscaActivity : AppCompatActivity() {
    private var binding: ActivityBiscaBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiscaBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        //configuração toolbar
        val toolbar = binding!!.toolbarContext.toolbar
        toolbar.title = "Bisca"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val adapter = FragmentPagerItemAdapter(supportFragmentManager, FragmentPagerItems.with(this)
                .add("Pontuação", PointsFragment::class.java)
                .add("Placar", ScoreboardFragment::class.java)
                .create())
        val viewPager = binding!!.viewpager
        viewPager.adapter = adapter
        binding!!.viewPagerTab.setViewPager(viewPager)
    }
}