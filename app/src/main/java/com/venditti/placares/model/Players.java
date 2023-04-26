package com.venditti.placares.model;

import com.google.firebase.database.DatabaseReference;
import com.venditti.placares.config.ConfigFireBase;
import com.venditti.placares.helper.DateCustom;

import java.io.Serializable;

public class Players implements Serializable {
    private String name;
    private Integer doing = -1;
    private Integer done = -1;
    private Integer points = 0;

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

    public Integer getDoing() {
        return doing;
    }

    public void setDoing(Integer doing) {
        this.doing = doing;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
