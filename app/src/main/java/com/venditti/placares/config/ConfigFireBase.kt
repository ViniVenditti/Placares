package com.venditti.placares.config

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.venditti.placares.helper.DateCustom

object ConfigFireBase {
    private var firebase: DatabaseReference? = null
    private var storage: StorageReference? = null
    val firebaseDatabase: DatabaseReference
        get() = if (firebase == null) FirebaseDatabase.getInstance().reference.also { firebase = it } else firebase!!
    val fireBaseStorage: StorageReference
        get() = if (storage == null) FirebaseStorage.getInstance().reference.also { storage = it } else storage!!
    val dataGameBiscaReference: DatabaseReference
        get() = firebaseDatabase
                .child("placares")
                .child("Bisca")
                .child(DateCustom.dataAtual())

    fun getPlayerRef(game: String?, partida: String?): DatabaseReference {
        return firebaseDatabase
                .child("placares")
                .child(game!!)
                .child(DateCustom.dataAtual())
                .child(partida!!)
    }
}