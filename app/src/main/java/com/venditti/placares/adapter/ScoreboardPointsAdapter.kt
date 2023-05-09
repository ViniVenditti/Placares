package com.venditti.placares.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.venditti.placares.R
import com.venditti.placares.model.Points

class ScoreboardPointsAdapter(private val listPoints: HashMap<String?, Points?>?, private val rodada: String) : RecyclerView.Adapter<ScoreboardPointsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val scoreboardPoints = LayoutInflater.from(parent.context).inflate(R.layout.points_scoreboard_layout, parent, false)
        return MyViewHolder(scoreboardPoints)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textRodadaScoreboard.text = rodada
        holder.textDoingScoreboard.text = listPoints!![rodada]?.doing.toString()
        holder.textDoneScoreboard.text = listPoints[rodada]?.done.toString()
        holder.textTotalScoreboard.text = listPoints[rodada]?.summation.toString()
    }

    override fun getItemCount(): Int {
        return listPoints!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textRodadaScoreboard: TextView
        var textDoingScoreboard: TextView
        var textDoneScoreboard: TextView
        var textTotalScoreboard: TextView

        init {
            textRodadaScoreboard = itemView.findViewById(R.id.textRodadaScoreboard)
            textDoingScoreboard = itemView.findViewById(R.id.textDoingScoreboard)
            textDoneScoreboard = itemView.findViewById(R.id.textDoneScoreboard)
            textTotalScoreboard = itemView.findViewById(R.id.textTotalScoreboard)
        }
    }
}