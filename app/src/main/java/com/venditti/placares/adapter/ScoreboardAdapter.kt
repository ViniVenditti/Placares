package com.venditti.placares.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venditti.placares.R
import com.venditti.placares.model.Players

class ScoreboardAdapter(private val context: Context?, private val listJogadores: List<Players>?) : RecyclerView.Adapter<ScoreboardAdapter.MyViewHolder>() {
    private var adapter: ScoreboardPointsAdapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val scoreboard = LayoutInflater.from(parent.context).inflate(R.layout.player_scoreboard_layout, parent, false)
        return MyViewHolder(scoreboard)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textPlayerScoreboard.text = listJogadores!![holder.adapterPosition].name
        holder.textTotalGeral.text = listJogadores[holder.adapterPosition].total.toString()
        val points = listJogadores[holder.adapterPosition].points
        if (!points!!.isEmpty()) {
            adapter = ScoreboardPointsAdapter(points, points.keys.toString())
            //Define Layout Categoria
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.recyclerPoints.layoutManager = layoutManager
            holder.recyclerPoints.setHasFixedSize(true)
            holder.recyclerPoints.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return listJogadores!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textPlayerScoreboard: TextView
        var textTotalGeral: TextView
        var recyclerPoints: RecyclerView

        init {
            textTotalGeral = itemView.findViewById(R.id.textTotalGeral)
            textPlayerScoreboard = itemView.findViewById(R.id.textPlayerScoreboard)
            recyclerPoints = itemView.findViewById(R.id.recyclerPoints)
        }
    }
}