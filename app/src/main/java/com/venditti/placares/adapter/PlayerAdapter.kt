package com.venditti.placares.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.venditti.placares.R
import com.venditti.placares.model.Players
import java.util.*

class PlayerAdapter(private val count: Int) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {
    private var context: Context? = null
    private val mapPlayers: MutableMap<Int?, Players?> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val players = LayoutInflater.from(parent.context).inflate(R.layout.jogador_layout, parent, false)
        context = parent.context
        return MyViewHolder(players)
    }

    fun getMapPlayers(): Map<Int?, Players?> {
        return mapPlayers
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.editPlayer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mapPlayers[holder.adapterPosition] = Players(Objects.requireNonNull(holder.editPlayer.text).toString())
            }
        })
    }

    override fun getItemCount(): Int {
        return count
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editPlayer: TextInputEditText

        init {
            editPlayer = itemView.findViewById(R.id.editJogador)
        }
    }
}