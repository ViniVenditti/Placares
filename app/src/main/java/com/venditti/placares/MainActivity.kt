package com.venditti.placares

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.venditti.placares.activities.BiscaActivity
import com.venditti.placares.adapter.PlayerAdapter
import com.venditti.placares.config.ConfigFireBase
import com.venditti.placares.databinding.ActivityMainBinding
import com.venditti.placares.model.Players
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var adapter: PlayerAdapter? = null
    private var game: String? = null
    private var gameCount: AtomicInteger? = null
    private var qtdPlayers: Int? = null

    override fun onStart() {
        super.onStart()
        gameCount = AtomicInteger(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //configuração toolbar
        val toolbar = binding!!.toolbarContext.toolbar
        toolbar.title = "Placares"
        setSupportActionBar(toolbar)

        carregaJogosPassados()

        game = binding!!.game.selectedItem.toString()
        val spinner = binding!!.numberPlayers
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                qtdPlayers = pos + 3
                carregarRecycleView(qtdPlayers!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding!!.btnPlay.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val mapPlayers = adapter!!.getMapPlayers()
        val players = ArrayList(mapPlayers!!.values)
        if (adapter!!.getMapPlayers().size > qtdPlayers!!) {
            Toast.makeText(applicationContext, "preencher nome dos jogadores", Toast.LENGTH_SHORT).show()
        } else {
            players.forEach(Consumer { p: Players? -> p!!.salvar(game, gameCount.toString()) })
        }
        val intent = Intent(applicationContext, BiscaActivity::class.java)
        intent.putExtra("listaJogadores", players)
        startActivity(intent)
    }

    private fun carregaJogosPassados() {
        ConfigFireBase.dataGameBiscaReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach(Consumer { s: DataSnapshot? -> gameCount!!.getAndIncrement() })
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun carregarRecycleView(count: Int) {
        val rv = binding!!.recyclerPlayer
        //configurar Adapter
        adapter = PlayerAdapter(count)

        //configurar RecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        rv.layoutManager = layoutManager
        rv.setHasFixedSize(false)
        rv.adapter = adapter
    }
}