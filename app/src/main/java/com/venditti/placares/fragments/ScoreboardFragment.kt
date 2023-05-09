package com.venditti.placares.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.venditti.placares.adapter.ScoreboardAdapter
import com.venditti.placares.config.ConfigFireBase
import com.venditti.placares.databinding.FragmentScoreboardBinding
import com.venditti.placares.model.Players

class ScoreboardFragment : Fragment() {
    private var binding: FragmentScoreboardBinding? = null
    private var adapter: ScoreboardAdapter? = null
    private var listPlayer: List<Players>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentScoreboardBinding.inflate(inflater)
        val dados = requireActivity().intent.extras
        listPlayer = dados!!.getSerializable("listaJogadores") as ArrayList<Players>?
        playerRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        configuraAdapter()
        return binding!!.root
    }

    private fun configuraAdapter() {
        adapter = ScoreboardAdapter(context, listPlayer)
        //Configura RecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerScoreboard.layoutManager = layoutManager
        binding!!.recyclerScoreboard.setHasFixedSize(true)
        binding!!.recyclerScoreboard.adapter = adapter
    }

    companion object {
        var playerRef = ConfigFireBase.dataGameBiscaReference
    }
}