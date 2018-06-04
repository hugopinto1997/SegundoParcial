package com.hugopinto.segundoparcial.Interfaces;

import android.view.ViewDebug;

import com.google.gson.annotations.Expose;

public class user {

    @Expose
    private int id;
    private String name;

    public user(){}

    public user(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
