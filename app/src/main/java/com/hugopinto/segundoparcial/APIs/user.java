package com.hugopinto.segundoparcial.APIs;

import android.view.ViewDebug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hugopinto.segundoparcial.Classes.Game;

import java.util.List;

public class user {

    private String usuario;
    private String pass;
    private String token;

    public user(String usuario, String pass){
        this.usuario=usuario;
        this.pass=pass;
    }
    private  user(String usuario, String pass, String token){
        this.usuario= usuario;
        this.pass=pass;
        this.token=token;
    }

   @SerializedName("_id")
    private String id;
   @SerializedName("__v")
    private int version;
   @SerializedName("favoriteNews")
    private List<Game> favoriteNews;
   @SerializedName("created_date")
    private String date;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Game> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(List<Game> favoriteNews) {
        this.favoriteNews = favoriteNews;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
