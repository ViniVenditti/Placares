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
import com.venditti.placares.adapter.PointsAdapter
import com.venditti.placares.config.ConfigFireBase
import com.venditti.placares.databinding.FragmentPointsBinding
import com.venditti.placares.model.Players
import java.util.function.Consumer

class PointsFragment : Fragment() {
    private var listPlayer: List<Players?>? = null
    private var adapter: PointsAdapter? = null
    private var binding: FragmentPointsBinding? = null
    private var rodada = 1
    private var rodadasSubindo = true
    private var maximoRodada = 0
    val key = arrayOf<String?>("")
    override fun onStart() {
        super.onStart()
        recoveryGame()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPointsBinding.inflate(inflater)
        val dados = requireActivity().intent.extras
        listPlayer = dados!!.getSerializable("listaJogadores") as ArrayList<Players?>?

        configuraAdapter()
        binding!!.txtRodada.text = "Rodada = $rodada"
        binding!!.btnCalcular.setOnClickListener { v: View? -> atualizaPontos() }
        qtdRodada
        return binding!!.root
    }

    private fun configuraAdapter() {
        adapter = PointsAdapter(listPlayer, rodada.toString())
        //Configura RecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding!!.recyclerView.layoutManager = layoutManager
        binding!!.recyclerView.setHasFixedSize(false)
        binding!!.recyclerView.adapter = adapter
    }

    private fun atualizaPontos() {
        if (rodadasSubindo) {
            rodada++
            adapter!!.setRodada(rodada.toString())
            binding!!.txtRodada.text = "Rodada = $rodada"
        } else {
            rodada--
            binding!!.txtRodada.text = "Rodada = " + rodada + "v"
            adapter!!.setRodada(rodada.toString() + "v")
        }
        if (rodada > maximoRodada) {
            rodadasSubindo = false
        } else if (rodada < 1) binding!!.txtRodada.text = "FIM DE JOGO"
        adapter?.listJogadores?.forEach(Consumer { p: Players? ->
            p?.total = p?.points?.values?.sumOf { it?.summation ?: 0 }!!

            ConfigFireBase.getPlayerRef("Bisca", key[0])
                    .child(p.name)
                    .setValue(p)
        })
        adapter!!.notifyDataSetChanged()
    }

    private fun recoveryGame() {
        ConfigFireBase.dataGameBiscaReference
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (s in snapshot.children) {
                            key[0] = s.key
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
    }

    private val qtdRodada: Unit
        get() {
            when (listPlayer!!.size) {
                3 -> maximoRodada = 17
                4 -> maximoRodada = 12
                5 -> maximoRodada = 10
                6 -> maximoRodada = 8
                7 -> maximoRodada = 7
                8 -> maximoRodada = 6
            }
        }
}