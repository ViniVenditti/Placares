package com.venditti.placares.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.venditti.placares.R
import com.venditti.placares.model.Players
import com.venditti.placares.model.Points

class PointsAdapter(val listJogadores: List<Players?>?, private var rodada: String) : RecyclerView.Adapter<PointsAdapter.MyViewHolder>() {
    fun setRodada(rodada: String) {
        this.rodada = rodada
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val placar = LayoutInflater.from(parent.context).inflate(R.layout.points_layout, parent, false)
        return MyViewHolder(placar)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listPoints = listJogadores!![holder.adapterPosition]?.points
        holder.editFaz.setText("")
        holder.editFez.setText("")
        holder.textPlayer.text = listJogadores[holder.adapterPosition]?.name
        holder.textPontos.text = listJogadores[holder.adapterPosition]?.total.toString()
        holder.editFez.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() == "") listPoints!!.remove(rodada)
            }

            override fun afterTextChanged(s: Editable) {
                val points = Points()
                if (s.toString() != "") {
                    val doing = Integer.valueOf(holder.editFaz.text.toString())
                    val done = Integer.valueOf(holder.editFez.text.toString())
                    points.doing = doing
                    points.done = done
                    points.summation = calculaPontuacao(doing, done)
                    listPoints!![rodada] = points
                    listJogadores[holder.adapterPosition]?.points = listPoints
                }
            }
        })
    }

    private fun calculaPontuacao(doing: Int, done: Int): Int {
        return if (done == doing) done + 10 else done
    }

    override fun getItemCount(): Int {
        return listJogadores!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editFaz: TextInputEditText
        var editFez: TextInputEditText
        var textPlayer: TextView
        var textPontos: TextView

        init {
            textPlayer = itemView.findViewById(R.id.textPlayer)
            editFaz = itemView.findViewById(R.id.editFaz)
            editFez = itemView.findViewById(R.id.editFez)
            textPontos = itemView.findViewById(R.id.textPontos)
        }
    }
}