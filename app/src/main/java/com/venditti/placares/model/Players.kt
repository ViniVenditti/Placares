package com.venditti.placares.model

import com.venditti.placares.config.ConfigFireBase
import com.venditti.placares.helper.DateCustom
import java.io.Serializable

class Players(name: String) : Serializable {
    var name: String
    var points: HashMap<String?, Points?>? = HashMap()
    var total = 0


    fun salvar(game: String?, gameCount: String?) {
        ConfigFireBase.firebaseDatabase
                .child("placares")
                .child(game!!)
                .child(DateCustom.dataAtual())
                .child(gameCount!!)
                .child(name!!)
                .setValue(this)
    }

    init {
        this.name = name
    }
}