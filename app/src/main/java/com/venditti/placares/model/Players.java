package com.venditti.placares.model;

import com.google.firebase.database.DatabaseReference;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.helper.DateCustom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Players implements Serializable {
    private String name;
    private HashMap<String, Points> points = new HashMap<>();
    private Integer total = 0;

    public Players() {
    }

    public void salvar(String game, String gameCount){
        DatabaseReference firebase = ConfigFireBase.getFirebaseDatabase();
        firebase.child("placares")
                .child(game)
                .child(DateCustom.dataAtual())
                .child(gameCount)
                .child(this.getName())
                .setValue(this);
    }

    public Players(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Points> getPoints() {
        return points;
    }

    public void setPoints(HashMap<String, Points> points) {
        this.points = points;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
