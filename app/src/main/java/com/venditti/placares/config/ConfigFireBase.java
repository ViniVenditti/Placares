package com.venditti.placares.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.venditti.placares.helper.DateCustom;

public class ConfigFireBase {
    private static DatabaseReference firebase;
    private static StorageReference storage;

    public static DatabaseReference getFirebaseDatabase(){
        return firebase == null ? firebase = FirebaseDatabase.getInstance().getReference() : firebase;
    }

    public static StorageReference getFireBaseStorage(){
        return storage == null ? storage = FirebaseStorage.getInstance().getReference() : storage;
    }

    public static DatabaseReference getDataGameBiscaReference(){
        return ConfigFireBase.getFirebaseDatabase()
                .child("placares")
                .child("Bisca")
                .child(DateCustom.dataAtual());
    }

}
